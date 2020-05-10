package com.vishalecho.android.dineout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishalecho.android.dineout.data.model.VenuesDataContract
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class VenueListViewModelFactory(private val repository: VenuesDataContract.Repository, private val compositeDisposable: CompositeDisposable) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VenueListViewModel(repository, compositeDisposable) as T
    }
}