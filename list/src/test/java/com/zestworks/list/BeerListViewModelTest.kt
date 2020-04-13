package com.zestworks.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.zestworks.common.LCE
import com.zestworks.common.LCE.Content
import com.zestworks.common.LCE.Error
import com.zestworks.common.LCE.Loading
import com.zestworks.data.beerlist.BeerListRepository
import com.zestworks.list.ui.BeerListViewModel
import com.zestworks.list.ui.BeerListViewState
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BeerListViewModelTest {

    private val mockRepository = mockk<BeerListRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val beerListViewModel = BeerListViewModel(
        mockRepository,
        testCoroutineDispatcher
    )
    private val mockObserver = mockk<Observer<LCE<BeerListViewState>>>(relaxed = true)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        beerListViewModel.beerListState.observeForever(mockObserver)
    }

    @Test
    fun isBeerListLoadingOrderCorrectly() = runBlockingTest {
        testWithSampleData(dummyCustomerPreference, expectedBeers)
    }

    @Test
    fun isBeerListLoadingOrderCorrectly2() = runBlockingTest {
        testWithSampleData(dummyCustomerPreference2, expectedBeers2)
    }

    @Test
    fun isBeerListLoadingOrderCorrectly3() = runBlockingTest {
        testWithSampleData(dummyCustomerPreference3, expectedBeers3)
    }

    @Test
    fun `is Beer list loading order correctly when order is not possible`() = runBlockingTest {
        testWithSampleData(dummyCustomerPreference4, expectedBeers4)
    }

    private fun testWithSampleData(
        dummyCustomerPreference: String,
        expectedBeers: BeerListViewState
    ) {
        coEvery { mockRepository.getCustomerPreferences() } returns dummyCustomerPreference
        coEvery { mockRepository.getBeerInfo(any()) } returns dummyBeerInfo
        beerListViewModel.onUILoad()
        verifyOrder {
            mockObserver.onChanged(Loading)
            mockObserver.onChanged(Content(expectedBeers))
        }
    }

    @Test
    fun isErrorHandled() = runBlockingTest {
        val reason = "Offline"
        coEvery { mockRepository.getCustomerPreferences() } throws Exception(reason)
        coEvery { mockRepository.getBeerInfo(any()) } returns dummyBeerInfo
        beerListViewModel.onUILoad()
        verifyOrder {
            mockObserver.onChanged(Loading)
            mockObserver.onChanged(Error(reason))
        }
    }
}