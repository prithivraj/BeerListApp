package com.zestworks.data.beerlist

interface BeerListRepository {
    suspend fun getCustomerPreferences(): String
}