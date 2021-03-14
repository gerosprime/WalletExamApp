package com.gerosprime.walletexamapp.presentation

import com.gerosprime.walletexamapp.domain.Result

class LoadingStateObserver<T> {
    var loading: () -> Unit = {}
    var error: (Throwable) -> Unit = {}
    var success: (T) -> Unit = {}

    fun updateState(result: Result<T>) {
        when (result) {
            is Result.Error -> error(result.throwable)
            Result.Loading -> loading()
            is Result.Success -> success(result.data)
        }
    }
}