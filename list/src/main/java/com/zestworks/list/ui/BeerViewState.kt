package com.zestworks.list.ui

internal sealed class BeerListViewState {
    data class ValidBeerListViewState(
        val listOfBeers: List<Beer>
    ): BeerListViewState()
    object InvalidBeerListViewState: BeerListViewState()
}

internal data class Beer(
    val imageUrl: String,
    val name: String,
    val abv: Double,
    val beerType: BeerType
)