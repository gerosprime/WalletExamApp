package com.gerosprime.walletexamapp.dagger

import com.gerosprime.walletexamapp.data.http.TransactionHistoryWebService
import com.gerosprime.walletexamapp.data.http.UsersWalletApi
import com.gerosprime.walletexamapp.data.http.WalletsWebService
import com.gerosprime.walletexamapp.data.http.retrofit.RetrofitUsersWalletApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class HttpModule {

    companion object {
        private const val USER_NAME = "glenn.devel"
        const val API_URL = "http://www.amock.io/api/$USER_NAME/"
    }

    @Provides
    @Singleton
    fun provideUsersWalletApi() : UsersWalletApi {
        return RetrofitUsersWalletApi(Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        )
    }

    @Provides
    @Singleton
    fun provideWalletWebservice(api : UsersWalletApi)
        : WalletsWebService = api.walletsWebService

    @Provides
    @Singleton
    fun provideTransactionHistoryWebservice(api : UsersWalletApi)
        : TransactionHistoryWebService = api.transactionHistoryWebService

}