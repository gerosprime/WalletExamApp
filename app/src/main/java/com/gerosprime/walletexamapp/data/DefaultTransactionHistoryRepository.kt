package com.gerosprime.walletexamapp.data

import com.gerosprime.walletexamapp.data.http.TransactionHistoryWebService
import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import com.gerosprime.walletexamapp.data.http.response.WalletLoadResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class DefaultTransactionHistoryRepository
    (private val webService: TransactionHistoryWebService) : TransactionHistoryRepository {
    override fun loadHistory(): Single<TransactionHistoryResponse> = Single.fromCallable {
        val call = webService.loadHistory().execute()
        if (call.isSuccessful) {
            return@fromCallable call.body() as TransactionHistoryResponse
        } else {
            val errorBody = call.errorBody() as ResponseBody
            throw HttpException(Response.error<WalletLoadResponse>(call.code(), errorBody))
        }
    }
}