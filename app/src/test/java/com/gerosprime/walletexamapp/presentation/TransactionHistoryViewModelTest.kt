package com.gerosprime.walletexamapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gerosprime.walletexamapp.LoadingState
import com.gerosprime.walletexamapp.domain.Transaction
import com.gerosprime.walletexamapp.presentation.viewmodels.TransactionHistoryViewModel
import com.gerosprime.walletexamapp.usecase.FakeLoadTransactionHistoryUseCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TransactionHistoryViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var subject: TransactionHistoryViewModel
    private lateinit var fakeUseCase: FakeLoadTransactionHistoryUseCase

    private lateinit var loadingState: LoadingState<List<Transaction>>

    @Before
    fun setUp() {
        loadingState = LoadingState()
        fakeUseCase = FakeLoadTransactionHistoryUseCase()
        subject = TransactionHistoryViewModel(fakeUseCase)
    }

    @Test
    fun loadHistory_success_notifiesObserverAndReceivesItem() {
        fakeUseCase.apply {
            mockError = false
        }
        subject.transactionHistoryLiveData.observeForever {
            loadingState.updateLoadState(it)
        }
        subject.loadHistory()

        with(loadingState) {
            assertTrue(loadingCalled)
            assertTrue(successCalled)
            assertNotNull(item)
        }
    }

    @Test
    fun loadHistory_error_notifiesObserverAndReceivesItem() {
        fakeUseCase.apply {
            mockError = true
        }
        subject.transactionHistoryLiveData.observeForever {
            loadingState.updateLoadState(it)
        }
        subject.loadHistory()

        with(loadingState) {
            assertTrue(loadingCalled)
            assertTrue(errorCalled)
            assertNull(item)
        }
    }
}