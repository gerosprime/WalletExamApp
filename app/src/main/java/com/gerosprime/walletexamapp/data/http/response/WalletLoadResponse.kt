package com.gerosprime.walletexamapp.data.http.response

import com.gerosprime.walletexamapp.domain.Wallet

class WalletLoadResponse(val wallets: List<Wallet> = listOf(),
                         val error: ResponseErrors? = null)