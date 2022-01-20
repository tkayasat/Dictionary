package com.example.dictionary.model.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface MySchedulers {

    fun main(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler

    fun newThread(): Scheduler

    fun single(): Scheduler

    fun trampoline(): Scheduler

    fun start()

    fun shutdown()
}