package com.vishalecho.android.dineout.di

import com.vishalecho.android.core.application.CoreApp
import javax.inject.Singleton

@Singleton
object DineoutDH {
    private var venueListComponent: VenueListComponent? = null

    fun venueListComponent() : VenueListComponent {
        if (venueListComponent == null)
            venueListComponent = DaggerVenueListComponent.builder().coreComponent(CoreApp.coreComponent).build()
        return venueListComponent as VenueListComponent
    }

    fun destroyVenueListComponent() {
        venueListComponent = null;
    }
}