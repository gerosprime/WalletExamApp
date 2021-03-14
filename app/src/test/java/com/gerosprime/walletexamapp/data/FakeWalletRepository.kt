package com.gerosprime.walletexamapp.data

import com.gerosprime.walletexamapp.data.http.response.ApiError
import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import com.gerosprime.walletexamapp.data.http.response.WalletLoadResponse
import io.reactivex.rxjava3.core.Single
import java.lang.RuntimeException

class FakeWalletRepository: WalletRepository {

    var error = false
    var apiError = false
    var mockResponse = WalletLoadResponse(listOf())

    var loadCalled = false

    override fun loadWallets(): Single<WalletLoadResponse> {
        loadCalled = true
        return when {
            error -> {
                Single.error(RuntimeException(""))
            }
            apiError -> {
                Single.error(ApiError(""))
            }
            else -> {
                Single.just(mockResponse)
            }
        }
    }
}