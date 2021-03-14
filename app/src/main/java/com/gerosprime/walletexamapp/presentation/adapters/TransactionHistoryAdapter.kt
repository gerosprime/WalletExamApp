package com.gerosprime.walletexamapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gerosprime.walletexamapp.R
import com.gerosprime.walletexamapp.domain.Transaction

class TransactionHistoryAdapter : RecyclerView.Adapter<TransactionHistoryViewHolder>() {

    var items: List<Transaction> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHistoryViewHolder
            = TransactionHistoryViewHolder(
        LayoutInflater.from(parent.context)
        .inflate(R.layout.viewholder_transaction_history_item, parent, false))

    override fun onBindViewHolder(holder: TransactionHistoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}