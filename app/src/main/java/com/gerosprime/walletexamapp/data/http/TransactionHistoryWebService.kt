package com.gerosprime.walletexamapp.data.http

import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import retrofit2.Call

interface TransactionHistoryWebService {
    fun loadHistory() : Call<TransactionHistoryResponse>
}