package com.gerosprime.walletexamapp.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gerosprime.walletexamapp.databinding.ViewholderWalletItemBinding
import com.gerosprime.walletexamapp.domain.Wallet

class WalletViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    private val binding = ViewholderWalletItemBinding.bind(itemView)

    fun bind(wallet: Wallet) {
        with(binding) {
            viewholderWalletItemName.text = wallet.walletName
            viewholderWalletItemBalance.text = wallet.balance
        }
    }

}