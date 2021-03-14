package com.gerosprime.walletexamapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gerosprime.walletexamapp.domain.Result
import com.gerosprime.walletexamapp.domain.Transaction
import com.gerosprime.walletexamapp.usecase.LoadTransactionHistoryUseCase

class TransactionHistoryViewModel(private val usecase: LoadTransactionHistoryUseCase)
    : ViewModel() {

    private var _transactionHistoryMLD = MutableLiveData<Result<List<Transaction>>>()
    val transactionHistoryLiveData: LiveData<Result<List<Transaction>>>
        get() = _transactionHistoryMLD

    init {
        usecase.apply {
            result = { transactionLoadSuccess(it) }
            error = { transactionLoadError(it) }
        }
    }

    fun loadHistory() {
        _transactionHistoryMLD.value = Result.Loading
        usecase.execute()
    }

    private fun transactionLoadError(error: Throwable) {
        _transactionHistoryMLD.value = Result.Error(error)
    }

    private fun transactionLoadSuccess(wallets: List<Transaction>) {
        _transactionHistoryMLD.value = Result.Success(wallets)
    }

}