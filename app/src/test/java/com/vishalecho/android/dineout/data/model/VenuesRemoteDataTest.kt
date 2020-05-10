package com.vishalecho.android.dineout.data.model

import android.os.Build
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vishalecho.android.dineout.data.DummyData.venuesResponse
import com.vishalecho.android.dineout.data.remote.ApiService
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Test for [VenuesRemoteData] class
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class VenuesRemoteDataTest {

    private val apiService = mock<ApiService>()

    @Test
    fun getRestaurants() {
        whenever(apiService.getRestaurants(hashMapOf())).thenReturn(
            Single.just(
                venuesResponse()
            )
        )

        VenuesRemoteData(apiService).getRestaurants().test().run {
            assertNoErrors()
            assertValueCount(1)
            Assert.assertEquals(values()[0].response.venues.size, 50)
            Assert.assertEquals(values()[0].response.venues[0].name, "Fresh Food Factory - Accha Khao Accha Khilao")
            Assert.assertEquals(values()[0].response.venues[0].id, "4d524ad1e0275481f939b3b6")
        }
    }

}