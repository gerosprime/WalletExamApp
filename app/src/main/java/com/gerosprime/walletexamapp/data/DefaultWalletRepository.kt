package com.gerosprime.walletexamapp.data

import android.os.SystemClock
import androidx.annotation.VisibleForTesting
import com.gerosprime.walletexamapp.data.http.WalletsWebService
import com.gerosprime.walletexamapp.data.http.response.ApiResponse
import com.gerosprime.walletexamapp.data.http.response.ApiStatus
import com.gerosprime.walletexamapp.data.http.response.WalletLoadResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class DefaultWalletRepository(private val walletsWebService: WalletsWebService)
    : WalletRepository {

    @VisibleForTesting
    var enableDelay = true

    override fun loadWallets(): Single<WalletLoadResponse> = Single.fromCallable {
        if (enableDelay) {
            SystemClock.sleep(10000)
        }
        val call = walletsWebService.getWallets().execute()
        if (call.isSuccessful) {
            val response = call.body() as ApiResponse<WalletLoadResponse>
            when (response.status) {
                ApiStatus.SUCCESS -> return@fromCallable response.data
                ApiStatus.ERROR -> throw response.mainApiError
            }
        } else {
            val errorBody = call.errorBody() as ResponseBody
            throw HttpException(Response.error<WalletLoadResponse>(call.code(), errorBody))
        }
    }
}