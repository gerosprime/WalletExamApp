package com.gerosprime.walletexamapp.data.http

import com.gerosprime.walletexamapp.data.http.response.ApiResponse
import com.gerosprime.walletexamapp.data.http.response.WalletLoadResponse
import retrofit2.Call

class FakeWalletsWebService : WalletsWebService {

    var mockError = false
    var apiError = false
    lateinit var mockResponse: WalletLoadResponse

    override fun getWallets(): Call<ApiResponse<WalletLoadResponse>> {
        val fakeCall = FakeCall<ApiResponse<WalletLoadResponse>>()
        fakeCall.mockError = mockError
        if (apiError) {
            fakeCall.mockResult = ApiResponse(400, RuntimeException())
        } else if (!mockError) {
            fakeCall.mockResult = ApiResponse(mockResponse)
        }
        return fakeCall
    }
}