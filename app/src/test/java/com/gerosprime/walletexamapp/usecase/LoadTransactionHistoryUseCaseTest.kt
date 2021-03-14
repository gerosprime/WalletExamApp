package com.gerosprime.walletexamapp.usecase

import com.gerosprime.walletexamapp.data.FakeTransactionHistoryRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoadTransactionHistoryUseCaseTest {

    private lateinit var subject: LoadTransactionHistoryUseCase

    private lateinit var repository: FakeTransactionHistoryRepository

    @Before
    fun setUp() {
        repository = FakeTransactionHistoryRepository()
        subject = LoadTransactionHistoryUseCase(repository = repository)
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

    @Test
    fun execute_apiError_notifiesObserverAndReceivesError() {
        repository.apply {
            apiError = true
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