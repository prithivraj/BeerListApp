package com.zestworks.list.di

import com.zestworks.list.BeerListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val beerListModule = module {
    viewModel { BeerListViewModel(get(), Dispatchers.IO) }
}