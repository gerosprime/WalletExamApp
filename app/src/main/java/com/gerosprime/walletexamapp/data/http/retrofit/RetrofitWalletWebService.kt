package com.gerosprime.walletexamapp.data.http.retrofit

import com.gerosprime.walletexamapp.data.http.WalletsWebService
import com.gerosprime.walletexamapp.data.http.response.WalletLoadResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitWalletWebService : WalletsWebService {

    @GET("wallets")
    override fun getWallets(): Call<WalletLoadResponse>
}