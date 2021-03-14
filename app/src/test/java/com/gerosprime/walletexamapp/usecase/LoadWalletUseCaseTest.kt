package com.gerosprime.walletexamapp.usecase

import com.gerosprime.walletexamapp.data.FakeWalletRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoadWalletUseCaseTest {

    private lateinit var subject: LoadWalletsUseCase
    private lateinit var repository: FakeWalletRepository

    @Before
    fun setUp() {
        repository = FakeWalletRepository()
        subject = LoadWalletsUseCase(repository = repository)
    }

    @Test
    fun execute_success_notifiesObserverAndReceivesResponse() {
        repository.apply {
            error = false
        }
        var resultCalled = false
        subject.result = {
            resultCalled = true
        }
        subject.execute()
        assertTrue(repository.loadCalled)
        assertTrue(resultCalled)
    }

    @Test
    fun execute_error_notifiesObserverAndReceivesError() {
        repository.apply {
            error = true
        }
        var errorCalled = false
        subject.error = {
            errorCalled = true
        }
        subject.execute()
        assertTrue(repository.loadCalled)
        assertTrue(errorCalled)
    }

}