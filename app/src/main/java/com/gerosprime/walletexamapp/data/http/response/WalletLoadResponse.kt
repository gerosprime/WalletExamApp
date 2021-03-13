package com.gerosprime.walletexamapp.data.http.response

class WalletLoadResponse(val wallets: List<WalletLoadResponse> = listOf(),
                         val error: ResponseErrors
)