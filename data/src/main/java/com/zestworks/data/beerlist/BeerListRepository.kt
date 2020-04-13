package com.zestworks.data.beerlist

interface BeerListRepository {
    suspend fun getCustomerPreferences(): String
    suspend fun getBeerInfo(beerNumber: Int): BeerListResponseModel
}