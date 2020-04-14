package com.zestworks.beerdetail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zestworks.common.LCE
import com.zestworks.common.LCE.Content
import com.zestworks.common.LCE.Error
import com.zestworks.common.LCE.Loading
import com.zestworks.data.beerlist.BeerListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

internal class BeerDetailViewModel(
    private val repository: BeerListRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _beerDetailStream = MutableLiveData<LCE<BeerDetailState>>()
    val beerDetailStream = _beerDetailStream as LiveData<LCE<BeerDetailState>>

    fun onUILoaded(beerID: Int) {
        if (_beerDetailStream.value != null) {
            return
        }
        viewModelScope.launch(coroutineDispatcher) {
            _beerDetailStream.postValue(Loading)
            try {
                val beerInfoResponse = repository.getBeerInfo(beerID)
                val beerInfo = beerInfoResponse.first()
                _beerDetailStream.postValue(
                    Content(
                        BeerDetailState(
                            imageURL = beerInfo.imageUrl,
                            name = beerInfo.name,
                            abv = beerInfo.abv.toString(),
                            description = beerInfo.description,
                            hops = beerInfo.ingredients.hops.fold(
                                "",
                                { acc, hop -> "$acc ${hop.add} ${hop.amount.value} ${hop.amount.unit} ${hop.attribute} ${hop.name}" }),
                            malts = beerInfo.ingredients.malt.fold(
                                "",
                                { acc, malt -> "$acc ${malt.name} ${malt.amount.value} ${malt.amount.unit} " }),
                            methods = "${beerInfo.method.fermentation.temp.value} ${beerInfo.method.fermentation.temp.unit}"
                        )
                    )
                )
            } catch (ex: Exception) {
                ex.message?.let { _beerDetailStream.postValue(Error(it)) }
            }

        }
    }
}