package com.gerosprime.walletexamapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gerosprime.walletexamapp.domain.Result
import com.gerosprime.walletexamapp.domain.Wallet
import com.gerosprime.walletexamapp.usecase.LoadWalletsUseCase

class WalletListViewModel(private val loadWalletsUseCase: LoadWalletsUseCase): ViewModel() {

    private var _walletListMLD = MutableLiveData<Result<List<Wallet>>>()
    val walletListLiveData: LiveData<Result<List<Wallet>>>
        get() = _walletListMLD

    init {
        loadWalletsUseCase.apply {
            result = { walletLoadSuccess(it) }
            error = { walletLoadError(it) }
        }
    }

    fun loadWallets() {
        _walletListMLD.value = Result.Loading
        loadWalletsUseCase.execute()
    }

    private fun walletLoadError(error: Throwable) {
        _walletListMLD.value = Result.Error(error)
    }

    private fun walletLoadSuccess(wallets: List<Wallet>) {
        _walletListMLD.value = Result.Success(wallets)
    }

}