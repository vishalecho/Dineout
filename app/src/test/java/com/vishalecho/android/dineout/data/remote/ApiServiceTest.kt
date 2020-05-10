package com.vishalecho.android.dineout.data.remote

import android.os.Build
import com.vishalecho.android.core.test.DependencyProvider
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

/**
 * Test for [ApiService] class
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ApiServiceTest {

    private lateinit var apiService: ApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun init() {
        mockWebServer = MockWebServer()
        apiService    = DependencyProvider.getRetrofit(mockWebServer.url("/")).create(ApiService::class.java)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getRestaurants() {
        queueResponse {
            setResponseCode(200)
            setBody(DependencyProvider.getResponseFromJson("venues"))
        }

        apiService.getRestaurants(hashMapOf())
            .test()
            .run {
                assertNoErrors()
                assertValueCount(1)
                Assert.assertEquals(values()[0].response.venues.size, 50)
                Assert.assertEquals(values()[0].response.venues[0].name, "Fresh Food Factory - Accha Khao Accha Khilao")
                Assert.assertEquals(values()[0].response.venues[0].id, "4d524ad1e0275481f939b3b6")
            }
    }

    private fun queueResponse(block: MockResponse.() -> Unit) {
        mockWebServer.enqueue(MockResponse().apply(block))
    }
}