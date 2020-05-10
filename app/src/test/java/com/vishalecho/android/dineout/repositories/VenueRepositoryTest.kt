package com.vishalecho.android.dineout.repositories

import android.os.Build
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.vishalecho.android.core.network.Response
import com.vishalecho.android.core.test.TestScheduler
import com.vishalecho.android.dineout.data.DummyData
import com.vishalecho.android.dineout.data.model.VenuesDataContract
import com.vishalecho.android.dineout.data.model.VenuesResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

/**
 * Tests for [VenueRepository]
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class VenueRepositoryTest {
    private val remote: VenuesDataContract.Remote = mock()

    private lateinit var repository: VenueRepository
    private val compositeDisposable = CompositeDisposable()

    @Before
    fun init() {
        repository = VenueRepository(remote, TestScheduler(), compositeDisposable)
        whenever(remote.getRestaurants()).doReturn(Single.just(DummyData.venuesResponse()))
    }

    /**
     * Verify if calling [VenueRepository.fetchRestaurants] triggers [VenuesDataContract.Remote.getRestaurants]
     *  and it's result is added to the [VenueRepository.restaurantsFetchResponse]
     * */
    @Test
    fun testFetchRestaurants() {
        val venues = DummyData.venuesResponse();
        whenever(remote.getRestaurants()).doReturn(Single.create { venues })

        val obs = TestObserver<Response<List<VenuesResponse.Response.Venue>>>()

        repository.restaurantsFetchResponse.subscribe(obs)
        obs.assertEmpty()

        repository.fetchRestaurants()
        verify(remote).getRestaurants()

        obs.assertValueAt(0, Response.loading(true))
    }

    /**
     * Verify erred refresh of restaurant pushes to [VenuesDataContract.Repository.restaurantsFetchResponse]
     * with error
     * */
    @Test
    fun testRefreshRestaurantsFailurePushesToOutcome() {
        val exception = IOException()
        whenever(remote.getRestaurants()).doReturn(Single.error(exception))

        val obs = TestObserver<Response<List<VenuesResponse.Response.Venue>>>()
        repository.restaurantsFetchResponse.subscribe(obs)

        repository.refreshRestaurants()

        obs.assertValueAt(0, Response.loading(true))
    }
}