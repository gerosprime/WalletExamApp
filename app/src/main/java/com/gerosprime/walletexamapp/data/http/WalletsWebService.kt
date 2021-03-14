package com.gerosprime.walletexamapp.data.http

import com.gerosprime.walletexamapp.data.http.response.ApiResponse
import com.gerosprime.walletexamapp.data.http.response.WalletLoadResponse
import retrofit2.Call

interface WalletsWebService {
    fun getWallets() : Call<ApiResponse<WalletLoadResponse>>
}