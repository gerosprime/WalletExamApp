package com.gerosprime.walletexamapp.dagger

import com.gerosprime.walletexamapp.data.TransactionHistoryRepository
import com.gerosprime.walletexamapp.data.WalletRepository
import com.gerosprime.walletexamapp.usecase.LoadTransactionHistoryUseCase
import com.gerosprime.walletexamapp.usecase.LoadWalletsUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideLoadWalletUseCase(@Named("uiScheduler") uiScheduler: Scheduler,
                                 @Named("ioScheduler") ioScheduler: Scheduler,
                                 repository: WalletRepository)
        : LoadWalletsUseCase = LoadWalletsUseCase(uiScheduler, ioScheduler, repository)

    @Provides
    @Singleton
    fun provideLoadTransactionHistoryUseCase(@Named("uiScheduler") uiScheduler: Scheduler,
                                 @Named("ioScheduler") ioScheduler: Scheduler,
                                 repository: TransactionHistoryRepository)
        : LoadTransactionHistoryUseCase = LoadTransactionHistoryUseCase(uiScheduler, ioScheduler, repository)

}