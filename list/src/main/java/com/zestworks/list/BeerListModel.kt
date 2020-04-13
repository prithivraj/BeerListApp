package com.zestworks.list

enum class BeerType {
    CLASSIC, BARREL
}

data class CustomerPreference(
    val beerNumber: Int,
    val beerType: BeerType
)

sealed class ItemStatus {
    object Unassigned : ItemStatus()
    data class Assigned(val beerType: BeerType, val beerNumber: Int) : ItemStatus()
}


sealed class BeerQueue {
    object ImpossibleQueue : BeerQueue()
    data class PossibleQueue(val size: Int, val beerStatus: List<ItemStatus> = List(size) { ItemStatus.Unassigned }) : BeerQueue()
}
