package com.zestworks.list

internal sealed class BeerListViewState {
    data class ValidBeerListViewState(
        val listOfBeers: List<Beer>
    ): BeerListViewState()
    object InvalidBeerListViewState: BeerListViewState()
}

data class Beer(
    val imageUrl: String,
    val name: String,
    val abv: Double,
    val beerType: BeerType
)