package com.gerosprime.walletexamapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gerosprime.walletexamapp.LoadingState
import com.gerosprime.walletexamapp.domain.Wallet
import com.gerosprime.walletexamapp.usecase.FakeLoadWalletsUseCase
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class WalletListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var subject: WalletListViewModel
    private lateinit var fakeUseCase: FakeLoadWalletsUseCase

    private lateinit var loadingState: LoadingState<List<Wallet>>

    @Before
    fun setUp() {
        loadingState = LoadingState()
        fakeUseCase = FakeLoadWalletsUseCase()
        subject = WalletListViewModel(fakeUseCase)
    }

    @Test
    fun loadWallets_success_notifiesObserverAndReceivesItem() {
        fakeUseCase.apply {
            mockError = false
        }
        subject.walletListLiveData.observeForever {
            loadingState.updateLoadState(it)
        }
        subject.loadWallets()

        with(loadingState) {
            assertTrue(loadingCalled)
            assertTrue(successCalled)
            assertNotNull(item)
        }
    }

    @Test
    fun loadWallets_error_notifiesObserverAndReceivesItem() {
        fakeUseCase.apply {
            mockError = true
        }
        subject.walletListLiveData.observeForever {
            loadingState.updateLoadState(it)
        }
        subject.loadWallets()

        with(loadingState) {
            assertTrue(loadingCalled)
            assertTrue(errorCalled)
            assertNull(item)
        }
    }

}