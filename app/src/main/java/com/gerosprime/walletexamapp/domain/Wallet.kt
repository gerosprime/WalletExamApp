package com.gerosprime.walletexamapp.domain

import com.google.gson.annotations.SerializedName

class Wallet(val id: String, @SerializedName("wallet_name") val walletName: String = "",
             val balance: String = "")