package com.gerosprime.walletexamapp.data

import com.gerosprime.walletexamapp.data.http.WalletsWebService
import com.gerosprime.walletexamapp.data.http.response.WalletLoadResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class DefaultWalletRepository(private val walletsWebService: WalletsWebService)
    : WalletRepository {
    override fun loadWallets(): Single<WalletLoadResponse> = Single.fromCallable {
        val call = walletsWebService.getWallets().execute()
        if (call.isSuccessful) {
            return@fromCallable call.body() as WalletLoadResponse
        } else {
            val errorBody = call.errorBody() as ResponseBody
            throw HttpException(Response.error<WalletLoadResponse>(call.code(), errorBody))
        }
    }
}