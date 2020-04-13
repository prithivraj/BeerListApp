package com.zestworks.data.di

import com.google.gson.Gson
import com.zestworks.data.beerlist.BeerListRepository
import com.zestworks.data.beerlist.NetworkBeerListRepository
import com.zestworks.data.network.GithubAPIService
import com.zestworks.data.network.PunkAPIService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val dataModule = module {
    single<BeerListRepository> { NetworkBeerListRepository(get(), get()) }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.punkapi.com")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(PunkAPIService::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(GithubAPIService::class.java)
    }
}