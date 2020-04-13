package com.zestworks.data.beerlist

import com.zestworks.data.network.GithubAPIService
import com.zestworks.data.network.PunkAPIService

internal class NetworkBeerListRepository(
    private val punkAPIService: PunkAPIService,
    private val githubAPIService: GithubAPIService
) : BeerListRepository {

    override suspend fun getCustomerPreferences(): String {
        return githubAPIService.getCustomerPreferences()
    }

    override suspend fun getBeerInfo(beerNumber: Int): BeerListResponseModel {
        return punkAPIService.getMovieDetails(beerNumber)
    }
}