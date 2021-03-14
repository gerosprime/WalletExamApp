package com.gerosprime.walletexamapp.data

import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import io.reactivex.rxjava3.core.Single
import java.lang.RuntimeException

class FakeTransactionHistoryRepository: TransactionHistoryRepository {

    var error = false
    var mockResponse = TransactionHistoryResponse(listOf())

    var loadCalled = false

    override fun loadHistory(): Single<TransactionHistoryResponse> {
        loadCalled = true
        return if (error) {
            Single.error(RuntimeException(""))
        } else {
            Single.just(mockResponse)
        }
    }
}