package com.gerosprime.walletexamapp.data

import com.gerosprime.walletexamapp.data.http.response.ApiError
import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import io.reactivex.rxjava3.core.Single
import java.lang.RuntimeException

class FakeTransactionHistoryRepository: TransactionHistoryRepository {

    var error = false
    var apiError = false
    var mockResponse = TransactionHistoryResponse(listOf())

    var loadCalled = false

    override fun loadHistory(): Single<TransactionHistoryResponse> {
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