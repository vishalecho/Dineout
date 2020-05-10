package com.vishalecho.android.dineout.data.remote

import com.vishalecho.android.dineout.data.model.VenuesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("venues/search")
    fun getRestaurants(@QueryMap filter: HashMap<String, String>): Single<VenuesResponse>
}