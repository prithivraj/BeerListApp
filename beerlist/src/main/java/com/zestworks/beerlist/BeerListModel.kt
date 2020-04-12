package com.zestworks.beerlist

data class BeerListViewState(
    val listOfBeers: List<Beer>
)

data class Beer(
    val imageUrl: String,
    val name: String,
    val abv: Float,
    val beerType: BeerType
)

enum class BeerType {
    CLASSIC, BARREL
}