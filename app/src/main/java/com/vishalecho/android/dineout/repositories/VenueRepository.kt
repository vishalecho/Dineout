package com.vishalecho.android.dineout.repositories

import com.vishalecho.android.core.extension.*
import com.vishalecho.android.core.network.Response
import com.vishalecho.android.core.network.Scheduler
import com.vishalecho.android.dineout.data.model.VenuesDataContract
import com.vishalecho.android.dineout.data.model.VenuesResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class VenueRepository(
    private val remote: VenuesDataContract.Remote,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : VenuesDataContract.Repository {

    override val restaurantsFetchResponse: PublishSubject<Response<List<VenuesResponse.Response.Venue>>> =
        PublishSubject.create<Response<List<VenuesResponse.Response.Venue>>>()

    override fun fetchRestaurants() {
        refreshRestaurants()
    }

    override fun refreshRestaurants() {
        restaurantsFetchResponse.loading(true)
        remote.getRestaurants()
            .performOnBackOutOnMain(scheduler)
            .subscribe({t ->
                restaurantsFetchResponse.success(t.response.venues)
            }, { error -> handleError(error)})
            .addTo(compositeDisposable)
    }

    override fun handleError(error: Throwable) {
        restaurantsFetchResponse.failed(error)
    }
}