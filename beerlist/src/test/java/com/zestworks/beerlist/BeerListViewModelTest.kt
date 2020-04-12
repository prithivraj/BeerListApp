package com.zestworks.beerlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.zestworks.common.LCE
import com.zestworks.common.LCE.Content
import com.zestworks.common.LCE.Loading
import com.zestworks.data.beerlist.BeerListRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BeerListViewModelTest {

    private val mockRepository = mockk<BeerListRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val beerListViewModel = BeerListViewModel(mockRepository, testCoroutineDispatcher)
    private val mockObserver = mockk<Observer<LCE<BeerListViewState>>>()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        beerListViewModel.beerListState.observeForever { mockObserver }
    }

    @Test
    fun isBeerListLoading() {
        coEvery { mockRepository.getCustomerPreferences() } returns "5\n" +
            "1 B 3 C 5C\n2 C 3 B 4C\n5B"
        val expectedBeers = listOf(
            Beer(
                "",
                "",
                0f,
                BeerType.CLASSIC
            ),
            Beer(
                "",
                "",
                0f,
                BeerType.CLASSIC
            ),
            Beer(
                "",
                "",
                0f,
                BeerType.CLASSIC
            ),
            Beer(
                "",
                "",
                0f,
                BeerType.CLASSIC
            ),
            Beer(
                "",
                "",
                0f,
                BeerType.BARREL
            )
        )
        beerListViewModel.onUILoad()
        verifyOrder {
            mockObserver.onChanged(Loading)
            mockObserver.onChanged(Content(BeerListViewState(expectedBeers)))
        }
    }
}