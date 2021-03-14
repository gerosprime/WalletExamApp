package com.gerosprime.walletexamapp.data.http.retrofit

import com.gerosprime.walletexamapp.data.http.TransactionHistoryWebService
import com.gerosprime.walletexamapp.data.http.response.ApiResponse
import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitWalletTransactionHistory : TransactionHistoryWebService {
    @GET("histories")
    override fun loadHistory(): Call<ApiResponse<TransactionHistoryResponse>>
}