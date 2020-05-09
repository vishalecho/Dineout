package com.vishalecho.android.core.di.module

import android.content.Context
import com.vishalecho.android.core.network.AppScheduler
import com.vishalecho.android.core.network.Scheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideScheduler(): Scheduler {
        return AppScheduler()
    }
}