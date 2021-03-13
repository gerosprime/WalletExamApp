package com.gerosprime.walletexamapp.data.http.retrofit

import com.gerosprime.walletexamapp.data.http.TransactionHistoryWebService
import com.gerosprime.walletexamapp.data.http.UsersWalletApi
import com.gerosprime.walletexamapp.data.http.WalletsWebService
import retrofit2.Retrofit

class RetrofitUsersWalletApi(retrofit: Retrofit) : UsersWalletApi {

    private val _walletsWebService = retrofit
            .create(RetrofitWalletWebService::class.java)
    private val _transactionHistoryWebService = retrofit
            .create(RetrofitWalletTransactionHistory::class.java)

    override val walletsWebService: WalletsWebService
        get() = _walletsWebService
    override val transactionHistoryWebService: TransactionHistoryWebService
        get() = _transactionHistoryWebService

}