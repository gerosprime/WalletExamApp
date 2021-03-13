package com.gerosprime.walletexamapp.data.http

import com.gerosprime.walletexamapp.data.http.response.WalletLoadResponse
import retrofit2.Call

class FakeWalletsWebService : WalletsWebService {

    var mockError = false
    lateinit var mockResponse: WalletLoadResponse

    override fun getWallets(): Call<WalletLoadResponse> {
        val fakeCall = FakeCall<WalletLoadResponse>()
        fakeCall.mockError = mockError
        if(!mockError) {
            fakeCall.mockResult = mockResponse
        }
        return fakeCall
    }
}