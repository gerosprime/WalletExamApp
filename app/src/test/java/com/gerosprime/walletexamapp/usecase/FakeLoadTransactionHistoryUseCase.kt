package com.gerosprime.walletexamapp.usecase

import com.gerosprime.walletexamapp.data.FakeTransactionHistoryRepository
import com.gerosprime.walletexamapp.domain.Wallet
import io.reactivex.rxjava3.core.Scheduler

class FakeLoadTransactionHistoryUseCase(uiScheduler: Scheduler? = null,
                                        ioScheduler: Scheduler? = null) :
    LoadTransactionHistoryUseCase(uiScheduler, ioScheduler, FakeTransactionHistoryRepository()) {

    var mockError = false
    var mockResponse: List<Wallet> = listOf()

    override fun execute() {
        if (mockError) {
            error(RuntimeException())
        } else {
            result(listOf())
        }
    }

}