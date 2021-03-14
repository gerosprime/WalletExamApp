package com.gerosprime.walletexamapp.usecase

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

open class RxUseCase(private val uiScheduler: Scheduler? = null,
                     private val ioScheduler: Scheduler? = null) {

    protected fun <T> setupSchedulersMaybe(maybe: Maybe<T>) : Maybe<T> {

        var result = maybe
        uiScheduler?.run {
            result = result.observeOn(uiScheduler)
        }
        ioScheduler?.run {
            result = result.observeOn(ioScheduler)
        }

        return result
    }

    protected fun <T> setupSchedulersSingle(single: Single<T>) : Single<T> {

        var result = single
        uiScheduler?.run {
            result = result.observeOn(uiScheduler)
        }
        ioScheduler?.run {
            result = result.observeOn(ioScheduler)
        }

        return result
    }

    protected fun setupSchedulersCompletable(completable: Completable) : Completable {
        var result = completable
        uiScheduler?.run {
            result = result.observeOn(uiScheduler)
        }
        ioScheduler?.run {
            result = result.observeOn(ioScheduler)
        }
        return result
    }

    open fun cancel() {

    }

}