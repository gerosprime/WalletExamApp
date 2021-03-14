package com.gerosprime.walletexamapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gerosprime.walletexamapp.R
import com.gerosprime.walletexamapp.domain.Wallet

class WalletAdapter : RecyclerView.Adapter<WalletViewHolder>() {

    var items: List<Wallet> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder
        = WalletViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_wallet_item, parent, false))

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}