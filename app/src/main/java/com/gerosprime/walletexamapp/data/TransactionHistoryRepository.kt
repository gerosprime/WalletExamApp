package com.gerosprime.walletexamapp.data

import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import io.reactivex.rxjava3.core.Single

interface TransactionHistoryRepository {
    fun loadHistory() : Single<TransactionHistoryResponse>
}