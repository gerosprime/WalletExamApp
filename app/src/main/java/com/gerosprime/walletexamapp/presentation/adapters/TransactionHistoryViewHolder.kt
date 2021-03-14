package com.gerosprime.walletexamapp.presentation.adapters

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gerosprime.walletexamapp.R
import com.gerosprime.walletexamapp.databinding.ViewholderTransactionHistoryItemBinding
import com.gerosprime.walletexamapp.domain.Transaction

class TransactionHistoryViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    private val binding = ViewholderTransactionHistoryItemBinding.bind(itemView)

    private val context = itemView.context
    private val entryStringMap = mapOf(Transaction.ENTRY_INCOMING
                                            to context.getString(R.string.incoming_format),
                                        Transaction.ENTRY_OUTGOING
                                            to context.getString(R.string.outcoming_format))

    private fun formatEntryString(history: Transaction) : String {
        val format = entryStringMap[history.entry] ?: ""
        return when (history.entry) {
            Transaction.ENTRY_INCOMING -> format
            Transaction.ENTRY_OUTGOING -> String.format(format, history.recipient)
            else -> ""
        }
    }

    @SuppressLint("SetTextI18n")
    fun bind(history: Transaction) {
        with(binding) {
            viewholderTransactionHistoryItemDesc.text = formatEntryString(history)
            viewholderTransactionHistoryItemBalance.text =
                    context.getString(R.string.transaction_balance_format, history.amount, history.currency)
        }
    }

}