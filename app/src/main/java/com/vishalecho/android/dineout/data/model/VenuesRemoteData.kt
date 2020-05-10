package com.vishalecho.android.dineout.data.model

import com.vishalecho.android.dineout.data.remote.ApiService
import com.vishalecho.android.dineout.utils.Constants
import io.reactivex.Single

class VenuesRemoteData(private val apiService: ApiService) : VenuesDataContract.Remote {

    override fun getRestaurants(): Single<VenuesResponse> = apiService.getRestaurants(getApiQueryParams())

    private fun getApiQueryParams() : HashMap<String, String> {
        val hashMap : HashMap<String, String> = HashMap<String, String> ()
        hashMap["client_id"] = Constants.CLIENT_ID
        hashMap["client_secret"] = Constants.CLIENT_SECRET
        hashMap["v"] = "20180323"
        hashMap["ll"] = "28.590158,77.313199"
        hashMap["query"] = "food"
        hashMap["limit"] = "50"
        return hashMap
    }
}