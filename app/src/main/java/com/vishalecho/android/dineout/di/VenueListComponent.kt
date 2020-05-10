package com.vishalecho.android.dineout.di

import com.vishalecho.android.core.di.component.CoreComponent
import com.vishalecho.android.core.network.Scheduler
import com.vishalecho.android.dineout.data.model.VenuesDataContract
import com.vishalecho.android.dineout.data.model.VenuesRemoteData
import com.vishalecho.android.dineout.data.remote.ApiService
import com.vishalecho.android.dineout.repositories.VenueRepository
import com.vishalecho.android.dineout.ui.venue.VenueListActivity
import com.vishalecho.android.dineout.ui.venue.VenueListAdapter
import com.vishalecho.android.dineout.viewmodel.VenueListViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@VenueListScope
@Component(dependencies = [CoreComponent::class], modules = [VenueListModule::class])
interface VenueListComponent {
    //Expose to dependent components
    fun apiServices() : ApiService
    fun scheduler()   : Scheduler

    fun inject(venueListActivity: VenueListActivity)
}

@Module
class VenueListModule {

    /*Adapter*/
    @Provides
    @VenueListScope
    fun adapter(): VenueListAdapter = VenueListAdapter()

    /*ViewModel*/
    @Provides
    @VenueListScope
    fun venueListViewModelFactory(repository: VenuesDataContract.Repository, compositeDisposable: CompositeDisposable): VenueListViewModelFactory = VenueListViewModelFactory(repository, compositeDisposable)

    /*Repository*/
    @Provides
    @VenueListScope
    fun venueRepo(remote: VenuesDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable): VenuesDataContract.Repository = VenueRepository(remote, scheduler, compositeDisposable)


    @Provides
    @VenueListScope
    fun remoteData(apiService: ApiService): VenuesDataContract.Remote = VenuesRemoteData(apiService)

    @Provides
    @VenueListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    /*Parent providers to dependents*/
    @Provides
    @VenueListScope
    fun apiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
