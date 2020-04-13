package com.zestworks.data.network

import com.zestworks.data.beerlist.BeerListResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PunkAPIService {
    @GET("/v2/beers/{id}")
    suspend fun getMovieDetails(@Path("id") beerID: Int): BeerListResponseModel
}