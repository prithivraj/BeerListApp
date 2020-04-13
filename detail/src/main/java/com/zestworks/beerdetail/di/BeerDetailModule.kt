package com.zestworks.beerdetail.di

import com.zestworks.beerdetail.ui.BeerDetailViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val beerDetailModule = module {
    viewModel {
        BeerDetailViewModel(
            get(),
            Dispatchers.IO
        )
    }
}