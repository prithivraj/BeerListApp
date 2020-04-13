package com.zestworks.list.ui

internal enum class BeerType {
    CLASSIC, BARREL
}

internal data class CustomerPreference(
    val beerNumber: Int,
    val beerType: BeerType
)

internal sealed class ItemStatus {
    object Unassigned : ItemStatus()
    data class Assigned(val beerType: BeerType, val beerNumber: Int) : ItemStatus()
}


internal sealed class BeerQueue {
    object ImpossibleQueue : BeerQueue()
    data class PossibleQueue(val size: Int, val beerStatus: List<ItemStatus> = List(size) { ItemStatus.Unassigned }) : BeerQueue()
}
