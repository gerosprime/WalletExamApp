package com.gerosprime.walletexamapp.data.http

import com.gerosprime.walletexamapp.data.http.response.ApiError
import com.gerosprime.walletexamapp.data.http.response.ApiResponse
import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import retrofit2.Call

class FakeTransactionHistoryService: TransactionHistoryWebService {

    var mockError = false
    var mockApiError = false
    lateinit var mockResponse: TransactionHistoryResponse

    override fun loadHistory(): Call<ApiResponse<TransactionHistoryResponse>> {
        val fakeCall = FakeCall<ApiResponse<TransactionHistoryResponse>>()
        return when {
            mockError -> {
                fakeCall.mockError = true
                fakeCall
            }
            mockApiError -> {
                fakeCall.mockResult = ApiResponse(400, ApiError(message = "Mock"))
                fakeCall
            }
            else -> {
                fakeCall.mockResult = ApiResponse(mockResponse)
                fakeCall
            }
        }
    }
}