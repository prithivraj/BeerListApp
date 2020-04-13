package com.zestworks.data.network

import com.zestworks.data.beerlist.BeerListResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GithubAPIService {
    @GET("LuigiPapinoDrop/d8ed153d5431bbad23e1e1c6b5ba1e3c/raw/4ec1c8064e51838240e941679d3ac063460685c2/code_challenge_richer.txt")
    suspend fun getCustomerPreferences(): String
}