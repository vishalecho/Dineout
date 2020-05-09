package com.vishalecho.android.core.network

import io.reactivex.Scheduler

/**
 *  Interface to mock threads during testing.
 * */
interface Scheduler {
    fun mainThread(): Scheduler
    fun io(): Scheduler
}