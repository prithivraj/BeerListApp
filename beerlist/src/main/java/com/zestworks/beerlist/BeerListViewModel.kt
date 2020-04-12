package com.zestworks.beerlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zestworks.common.LCE
import com.zestworks.data.beerlist.BeerListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class BeerListViewModel(
    private val beerListRepository: BeerListRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _beerListState = MutableLiveData<LCE<BeerListViewState>>()
    val beerListState = _beerListState as LiveData<LCE<BeerListViewState>>

    fun onUILoad() {
        viewModelScope.launch(dispatcher) {

        }
    }
}