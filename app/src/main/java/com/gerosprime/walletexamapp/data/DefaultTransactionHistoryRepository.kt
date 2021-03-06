package com.gerosprime.walletexamapp.data

import android.os.SystemClock
import androidx.annotation.VisibleForTesting
import com.gerosprime.walletexamapp.data.http.TransactionHistoryWebService
import com.gerosprime.walletexamapp.data.http.response.ApiResponse
import com.gerosprime.walletexamapp.data.http.response.ApiStatus
import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import com.gerosprime.walletexamapp.data.http.response.WalletLoadResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class DefaultTransactionHistoryRepository
    (private val webService: TransactionHistoryWebService) : TransactionHistoryRepository {

    @VisibleForTesting
    var enableDelay = true

    override fun loadHistory(): Single<TransactionHistoryResponse> = Single.fromCallable {

        if (enableDelay) {
            SystemClock.sleep(7000)
        }

        val call = webService.loadHistory().execute()
        if (call.isSuccessful) {
            val response = call.body() as ApiResponse<TransactionHistoryResponse>
            when (response.responseStatus) {
                ApiStatus.SUCCESS -> return@fromCallable response.data
                ApiStatus.ERROR -> throw response.mainApiError
            }
        } else {
            val errorBody = call.errorBody() as ResponseBody
            throw HttpException(Response.error<WalletLoadResponse>(call.code(), errorBody))
        }
    }
}