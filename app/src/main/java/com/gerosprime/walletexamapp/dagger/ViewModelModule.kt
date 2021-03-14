package com.gerosprime.walletexamapp.dagger

import androidx.lifecycle.ViewModel
import com.gerosprime.walletexamapp.presentation.viewmodels.TransactionHistoryViewModel
import com.gerosprime.walletexamapp.presentation.viewmodels.WalletListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WalletListViewModel::class)
    fun provideWalletListViewModel
                (viewModel : WalletListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionHistoryViewModel::class)
    fun provideTransactionHistoryViewModel
                (viewModel : TransactionHistoryViewModel): ViewModel

}