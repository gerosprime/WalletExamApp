package com.gerosprime.walletexamapp

import com.gerosprime.walletexamapp.domain.Result

class LoadingState<T> {


    var item: T? = null
    var error: Throwable? = null
    var loadingCalled = false
    var errorCalled = false
    var successCalled = false

    fun updateLoadState(result: Result<T>) {
        when(result) {
            is Result.Loading -> {
                loadingCalled = true
            }
            is Result.Success -> {
                successCalled = true
                item = result.data
            }
            is Result.Error -> {
                errorCalled = true
                error = result.throwable
            }

        }
    }

}