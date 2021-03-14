package com.gerosprime.walletexamapp.data.http

import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import retrofit2.Call

class FakeTransactionHistoryService: TransactionHistoryWebService {

    var mockError = false
    lateinit var mockResponse: TransactionHistoryResponse

    override fun loadHistory(): Call<TransactionHistoryResponse> {
        val fakeCall = FakeCall<TransactionHistoryResponse>()
        return if (mockError) {
            fakeCall.mockError = true
            fakeCall
        } else {
            fakeCall.mockResult = mockResponse
            fakeCall
        }
    }
}