package com.zestworks.list.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zestworks.common.LCE
import com.zestworks.common.LCE.Content
import com.zestworks.common.LCE.Error
import com.zestworks.common.LCE.Loading
import com.zestworks.data.beerlist.BeerListRepository
import com.zestworks.list.ui.BeerListViewState.InvalidBeerListViewState
import com.zestworks.list.ui.BeerListViewState.ValidBeerListViewState
import com.zestworks.list.ui.ItemStatus.Unassigned
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

internal class BeerListViewModel(
    private val beerListRepository: BeerListRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _beerListState = MutableLiveData<LCE<BeerListViewState>>()
    val beerListState = _beerListState as LiveData<LCE<BeerListViewState>>

    fun onUILoad() {
        if (_beerListState.value != null) {
            return
        }
        viewModelScope.launch(dispatcher) {
            _beerListState.postValue(Loading)
            try {
                val customerPreferencesResponse = beerListRepository.getCustomerPreferences()
                val inputLines = customerPreferencesResponse.split("\n")
                val numberOfBeers = inputLines.first().toInt()
                val mutableInput = inputLines.toMutableList()
                mutableInput.removeAt(0)
                val inputs = mutableInput.toTypedArray()
                when (val beerQueue =
                    computeQueue(inputs, numberOfBeers)) {
                    BeerQueue.ImpossibleQueue -> {
                        _beerListState.postValue(Content(InvalidBeerListViewState))
                    }
                    is BeerQueue.PossibleQueue -> {
                        val listOfBeers = beerQueue.beerStatus.mapIndexed { index, itemStatus ->
                            val beerInfo = beerListRepository.getBeerInfo(index + 1).first()
                            val beerType =
                                if (itemStatus is Unassigned) BeerType.CLASSIC else (itemStatus as ItemStatus.Assigned).beerType
                            Beer(
                                id = beerInfo.id,
                                imageUrl = beerInfo.imageUrl,
                                name = beerInfo.name,
                                abv = beerInfo.abv,
                                beerType = beerType
                            )
                        }
                        _beerListState.postValue(
                            Content(
                                ValidBeerListViewState(
                                    listOfBeers
                                )
                            )
                        )
                    }
                }
            } catch (exception: Exception) {
                exception.message?.let { _beerListState.postValue(Error(it)) }
            }

        }
    }
}

