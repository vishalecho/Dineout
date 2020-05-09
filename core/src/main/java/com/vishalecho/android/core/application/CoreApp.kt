package com.vishalecho.android.core.application

import android.app.Application
import com.facebook.stetho.Stetho
import com.vishalecho.android.core.BuildConfig
import com.vishalecho.android.core.di.component.CoreComponent
import com.vishalecho.android.core.di.component.DaggerCoreComponent
import com.vishalecho.android.core.di.module.AppModule

open class CoreApp : Application() {

    companion object {
        lateinit var coreComponent: CoreComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
        initStetho()
    }

    private fun initDI() {
        coreComponent = DaggerCoreComponent.builder().appModule(AppModule(this)).build()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }
}