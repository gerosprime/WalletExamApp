package com.gerosprime.walletexamapp.data

import com.gerosprime.walletexamapp.data.http.response.WalletLoadResponse
import io.reactivex.rxjava3.core.Single

interface WalletRepository {
    fun loadWallets() : Single<WalletLoadResponse>
}