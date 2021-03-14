package com.gerosprime.walletexamapp.usecase

import com.gerosprime.walletexamapp.data.FakeWalletRepository
import com.gerosprime.walletexamapp.domain.Wallet
import io.reactivex.rxjava3.core.Scheduler

class FakeLoadWalletsUseCase(uiScheduler: Scheduler? = null, ioScheduler: Scheduler? = null) :
    LoadWalletsUseCase(uiScheduler, ioScheduler, FakeWalletRepository()) {

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