package com.gerosprime.walletexamapp.dagger

import com.gerosprime.walletexamapp.data.DefaultTransactionHistoryRepository
import com.gerosprime.walletexamapp.data.DefaultWalletRepository
import com.gerosprime.walletexamapp.data.TransactionHistoryRepository
import com.gerosprime.walletexamapp.data.WalletRepository
import com.gerosprime.walletexamapp.data.http.TransactionHistoryWebService
import com.gerosprime.walletexamapp.data.http.WalletsWebService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideWalletRepository(walletsWebService: WalletsWebService)
            : WalletRepository = DefaultWalletRepository(walletsWebService)

    @Provides
    @Singleton
    fun provideTransactionHistoryRepository(webService: TransactionHistoryWebService)
            : TransactionHistoryRepository = DefaultTransactionHistoryRepository(webService)

}