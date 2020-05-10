package com.vishalecho.android.dineout.data.model

import com.vishalecho.android.core.network.Response
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface VenuesDataContract {
    interface Repository {
        val restaurantsFetchResponse: PublishSubject<Response<List<VenuesResponse.Response.Venue>>>
        fun fetchRestaurants()
        fun refreshRestaurants()
        fun handleError(error: Throwable)
    }

    interface Remote {
        fun getRestaurants():Single<VenuesResponse>
    }
}