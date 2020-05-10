package com.vishalecho.android.dineout.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vishalecho.android.core.extension.toLiveData
import com.vishalecho.android.core.network.Response
import com.vishalecho.android.dineout.data.model.VenuesDataContract
import com.vishalecho.android.dineout.data.model.VenuesResponse
import com.vishalecho.android.dineout.di.DineoutDH
import io.reactivex.disposables.CompositeDisposable

class VenueListViewModel(private val repo: VenuesDataContract.Repository,
                         private val compositeDisposable: CompositeDisposable) : ViewModel() {

    val venuesResponse: LiveData<Response<List<VenuesResponse.Response.Venue>>> by lazy {
        //Convert publish subject to LiveData
        repo.restaurantsFetchResponse.toLiveData(compositeDisposable)
    }

    fun getRestaurants() {
        if (venuesResponse.value == null)
            repo.fetchRestaurants();
    }

    fun refreshRestaurants() {
        repo.refreshRestaurants();
    }

    override fun onCleared() {
        super.onCleared()
        //Clear the disposable when the ViewModel is cleared
        compositeDisposable.clear()
        DineoutDH.destroyVenueListComponent()
    }
}