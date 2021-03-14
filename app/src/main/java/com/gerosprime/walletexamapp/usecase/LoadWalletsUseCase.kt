package com.gerosprime.walletexamapp.usecase

import com.gerosprime.walletexamapp.data.WalletRepository
import com.gerosprime.walletexamapp.domain.Wallet
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable

open class LoadWalletsUseCase(uiScheduler: Scheduler? = null, ioScheduler: Scheduler? = null,
                        private val repository: WalletRepository) :
    RxUseCase(uiScheduler, ioScheduler) {

    var result: (List<Wallet>) -> Unit = {}
    var error: (Throwable) -> Unit = {}

    private var disposable = Disposable.disposed()
    open fun execute() {
        val single = setupSchedulersSingle(repository.loadWallets().map { it.wallets })
        disposable = single.subscribe(result, error)
    }

    override fun cancel() {
        super.cancel()
        disposable.dispose()
    }

}