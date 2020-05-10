package com.vishalecho.android.dineout.viewmodel

import android.os.Build
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.vishalecho.android.core.network.Response
import com.vishalecho.android.dineout.data.DummyData
import com.vishalecho.android.dineout.data.model.VenuesDataContract
import com.vishalecho.android.dineout.data.model.VenuesResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

/**
 * Test for [VenueListViewModel] class
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class VenueListViewModelTest {

    private lateinit var viewModel: VenueListViewModel

    private val repo: VenuesDataContract.Repository = mock()

    private val response: Observer<Response<List<VenuesResponse.Response.Venue>>> = mock()

    @Before
    fun init() {
        viewModel = VenueListViewModel(repo, CompositeDisposable())
        whenever(repo.restaurantsFetchResponse).doReturn(PublishSubject.create())
        viewModel.venuesResponse.observeForever(response)
    }


    /**
     * Test [VenueListViewModel.getRestaurants] triggers [VenuesDataContract.Repository.fetchRestaurants] &
     * liveData [VenueListViewModel.venuesResponse] gets responses pushed
     * from [VenuesDataContract.Repository.restaurantsFetchResponse]
     * */
    @Test
    fun testGetRestaurantsSuccess() {
        viewModel.getRestaurants()
        verify(repo).fetchRestaurants()

        repo.restaurantsFetchResponse.onNext(Response.loading(true))
        verify(response).onChanged(Response.loading(true))

        repo.restaurantsFetchResponse.onNext(Response.loading(false))
        verify(response).onChanged(Response.loading(false))

        val data = listOf(DummyData.venue())
        repo.restaurantsFetchResponse.onNext(Response.success(data))
        verify(response).onChanged(Response.success(data))
    }

    /**
     * Test that [VenuesDataContract.Repository.restaurantsFetchResponse] on exception passes exception to
     * live [VenueListViewModel.venuesResponse]
     * */
    @Test
    fun testGetRestaurantsError() {
        val exception = IOException()
        repo.restaurantsFetchResponse.onNext(Response.failure(exception))
        verify(response).onChanged(Response.failure(exception))
    }

    /**
     * Verify [VenueListViewModel.refreshRestaurants] triggers [VenuesDataContract.Repository.refreshRestaurants]
     * */
    @Test
    fun testRefreshRestaurants() {
        viewModel.refreshRestaurants()
        verify(repo).refreshRestaurants()
    }

}