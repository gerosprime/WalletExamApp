package com.gerosprime.walletexamapp.usecase

import com.gerosprime.walletexamapp.data.TransactionHistoryRepository
import com.gerosprime.walletexamapp.domain.Transaction
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable

open class LoadTransactionHistoryUseCase(uiScheduler: Scheduler? = null, ioScheduler: Scheduler? = null,
                                    private val repository: TransactionHistoryRepository) :
    RxUseCase(uiScheduler, ioScheduler) {

    var result: (List<Transaction>) -> Unit = {}
    var error: (Throwable) -> Unit = {}

    private var disposable = Disposable.disposed()

    open fun execute() {
        val single = setupSchedulersSingle(repository.loadHistory().map { it.histories })
        disposable = single.subscribe(result, error)
    }

    override fun cancel() {
        super.cancel()
        disposable.dispose()
    }

}