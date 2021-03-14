package com.gerosprime.walletexamapp.domain

class Transaction(val id: String, val entry: String, val amount: String,
                  val currency: String,
                  val sender: String, val recipient: String) {

    companion object {
        const val ENTRY_INCOMING = "incoming"
        const val ENTRY_OUTGOING = "outgoing"
    }

}