package com.gerosprime.walletexamapp.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.gerosprime.walletexamapp.R
import com.gerosprime.walletexamapp.databinding.*
import com.gerosprime.walletexamapp.domain.Transaction
import com.gerosprime.walletexamapp.domain.Wallet
import com.gerosprime.walletexamapp.presentation.adapters.TransactionHistoryAdapter
import com.gerosprime.walletexamapp.presentation.adapters.WalletAdapter
import com.gerosprime.walletexamapp.presentation.viewmodels.TransactionHistoryViewModel
import com.gerosprime.walletexamapp.presentation.viewmodels.WalletListViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class WalletMainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    private lateinit var walletListViewModel: WalletListViewModel
    private lateinit var transactionHistoryViewModel: TransactionHistoryViewModel

    private var loadingStateWallet = LoadingStateObserver<List<Wallet>>()
    private var loadingStateTransactions = LoadingStateObserver<List<Transaction>>()

    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var walletBinding: LayoutWalletListBinding
    private lateinit var walletLoadingBinding: LayoutContentLoadingBinding
    private lateinit var walletErrorBinding: LayoutContentErrorBinding

    private lateinit var transactionBinding: LayoutTransactionListBinding
    private lateinit var transactionLoadingBinding: LayoutContentLoadingBinding
    private lateinit var transactionErrorBinding: LayoutContentErrorBinding

    private val walletAdapter = WalletAdapter()
    private val transactionHistoryAdapter = TransactionHistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        val viewModelProvider = ViewModelProvider(this, viewModelFactory)

        walletListViewModel = viewModelProvider[WalletListViewModel::class.java]
        transactionHistoryViewModel = viewModelProvider[TransactionHistoryViewModel::class.java]

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(mainBinding.root)
        setSupportActionBar(mainBinding.toolbar)

        with(mainBinding) {

            walletBinding = activityMainWalletContent
            with(walletBinding) {
                root.addItemDecoration(DividerItemDecoration(this@WalletMainActivity,
                    DividerItemDecoration.VERTICAL))
                root.adapter = walletAdapter
            }

            walletLoadingBinding = activityMainWalletContentLoading
            walletErrorBinding = activityMainWalletContentError
            with(walletErrorBinding) {
                layoutContentErrorButton.text = getString(R.string.retry)
                layoutContentErrorButton.setOnClickListener { walletListViewModel.loadWallets() }
            }

            transactionBinding = activityMainTransactionContent
            with(transactionBinding) {
                root.addItemDecoration(DividerItemDecoration(this@WalletMainActivity,
                    DividerItemDecoration.VERTICAL))
                root.adapter = transactionHistoryAdapter
            }
            transactionLoadingBinding = activityMainTransactionContentLoading
            transactionErrorBinding = activityMainTransactionContentError
            with(transactionErrorBinding) {
                layoutContentErrorButton.text = getString(R.string.retry)
                layoutContentErrorButton.setOnClickListener { transactionHistoryViewModel.loadHistory() }
            }


        }

        attachObservers()

        performWalletLoadIfNeeded(savedInstanceState)
        performTransactionHistoryIfNeeded(savedInstanceState)
    }

    private fun attachObservers() {
        with(loadingStateWallet) {
            loading = { walletListLoading() }
            success = { walletListLoadSuccess(it) }
            error = { walletListLoadError(it) }
        }
        with(loadingStateTransactions) {
            loading = { transactionsHistoryLoading() }
            success = { transactionsHistoryLoadSuccess(it) }
            error = { transactionsHistoryLoadError(it) }
        }
        with(walletListViewModel) {
            walletListLiveData.observe(this@WalletMainActivity, {
                loadingStateWallet.updateState(it)
            })
        }
        with(transactionHistoryViewModel) {
            transactionHistoryLiveData.observe(this@WalletMainActivity, {
                loadingStateTransactions.updateState(it)
            })
        }
    }

    private fun walletListLoadError(error: Throwable) {
        walletBinding.apply {
            root.visibility = View.INVISIBLE
        }
        walletErrorBinding.apply {
            root.visibility = View.VISIBLE
            layoutContentErrorMessage.text = error.localizedMessage
        }
        walletLoadingBinding.apply {
            root.visibility = View.INVISIBLE
        }
    }

    private fun walletListLoadSuccess(wallets: List<Wallet>) {

        with(walletAdapter) {
            items = wallets
            notifyDataSetChanged()
        }

        walletBinding.apply {
            root.visibility = View.VISIBLE
        }
        walletErrorBinding.apply {
            root.visibility = View.INVISIBLE
        }
        walletLoadingBinding.apply {
            root.visibility = View.INVISIBLE
        }
    }

    private fun walletListLoading() {
        walletBinding.apply {
            root.visibility = View.INVISIBLE
        }
        walletErrorBinding.apply {
            root.visibility = View.INVISIBLE
        }
        walletLoadingBinding.apply {
            root.visibility = View.VISIBLE
        }
    }

    private fun transactionsHistoryLoadError(error: Throwable) {
        transactionBinding.apply {
            root.visibility = View.INVISIBLE
        }
        transactionErrorBinding.apply {
            root.visibility = View.VISIBLE
            layoutContentErrorMessage.text = error.localizedMessage
        }
        transactionLoadingBinding.apply {
            root.visibility = View.INVISIBLE
        }
    }

    private fun transactionsHistoryLoadSuccess(transactions: List<Transaction>) {

        with(transactionHistoryAdapter) {
            items = transactions
            notifyDataSetChanged()
        }

        transactionBinding.apply {
            root.visibility = View.VISIBLE
        }
        transactionErrorBinding.apply {
            root.visibility = View.INVISIBLE
        }
        transactionLoadingBinding.apply {
            root.visibility = View.INVISIBLE
        }
    }

    private fun transactionsHistoryLoading() {
        transactionBinding.apply {
            root.visibility = View.INVISIBLE
        }
        transactionErrorBinding.apply {
            root.visibility = View.INVISIBLE
        }
        transactionLoadingBinding.apply {
            root.visibility = View.VISIBLE
        }
    }
    
    private fun performWalletLoadIfNeeded(savedInstanceState: Bundle?) {
        with(walletListViewModel) {
            val existingValue = walletListLiveData.value
            if (savedInstanceState == null || existingValue == null) {
                loadWallets()
            }
        }
    }

    private fun performTransactionHistoryIfNeeded(savedInstanceState: Bundle?) {
        with(transactionHistoryViewModel) {
            val existingValue = transactionHistoryLiveData.value
            if (savedInstanceState == null || existingValue == null) {
                loadHistory()
            }
        }
    }

}