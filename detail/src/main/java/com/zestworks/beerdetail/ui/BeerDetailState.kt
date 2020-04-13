package com.zestworks.beerdetail.ui

internal data class BeerDetailState(
    val imageURL: String,
    val name: String,
    val abv: String,
    val description: String,
    val hops: String,
    val malts: String,
    val methods: String
)