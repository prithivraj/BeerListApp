package com.zestworks.list.ui

internal fun computeQueue(inputs: Array<String>, numberOfBeers: Int): BeerQueue {
    val inputModel = inputs.map { string ->
        val groupBy = string.filterNot {
            it == ' '
        }.groupBy {
            it.isDigit()
        }
        val preferences = mutableListOf<CustomerPreference>()
        val digitsList = groupBy[true]
        val beersList = groupBy[false]
        digitsList?.let { digits ->
            beersList?.let { beers ->
                digits.forEachIndexed { index, _ ->
                    preferences.add(
                        CustomerPreference(
                            beerNumber = Integer.parseInt(digits[index].toString()),
                            beerType = if (beers[index] == 'C') BeerType.CLASSIC else BeerType.BARREL
                        )
                    )
                }
            }
        }
        preferences as List<CustomerPreference>
    }

    val sortedInputModel = mutableListOf<List<CustomerPreference>>()

    inputModel.forEach { list ->
        sortedInputModel.add(
            list.sortedBy {
                if (it.beerType == BeerType.CLASSIC) {
                    1
                } else {
                    2
                }
            }
        )
    }

    return findBestQueue(
        BeerQueue.PossibleQueue(
            numberOfBeers
        ), sortedInputModel, 0
    )
}

private fun findBestQueue(
    queue: BeerQueue,
    customers: List<List<CustomerPreference>>,
    startIndex: Int
): BeerQueue {

    if (queue is BeerQueue.ImpossibleQueue) {
        return queue
    } else {
        val currentQueue = queue as BeerQueue.PossibleQueue
        val currentCustomer = customers[startIndex]
        currentCustomer.forEach {
            when (val potential = currentQueue.beerStatus[it.beerNumber - 1]) {
                ItemStatus.Unassigned -> {
                    // Now there are 2 cases - when this preference is considered and when this is not
                    val newQueue = currentQueue.beerStatus.toMutableList()
                    newQueue[it.beerNumber - 1] =
                        ItemStatus.Assigned(
                            it.beerType,
                            it.beerNumber
                        )
                    val copy = currentQueue.copy(beerStatus = newQueue)
                    if (startIndex == customers.size - 1) {
                        return copy
                    } else {
                        val bestQueueWithThisPref =
                            findBestQueue(
                                copy,
                                customers,
                                startIndex + 1
                            )
                        if (bestQueueWithThisPref is BeerQueue.PossibleQueue) {
                            return bestQueueWithThisPref
                        }
                    }
                }
                is ItemStatus.Assigned -> {
                    val beerType = potential.beerType
                    if (beerType == it.beerType) {
                        return if (startIndex == customers.size - 1) {
                            queue
                        } else {
                            findBestQueue(
                                queue,
                                customers,
                                startIndex + 1
                            )
                        }
                    } else {
                        return@forEach
                    }
                }
            }
        }
        return BeerQueue.ImpossibleQueue
    }
}