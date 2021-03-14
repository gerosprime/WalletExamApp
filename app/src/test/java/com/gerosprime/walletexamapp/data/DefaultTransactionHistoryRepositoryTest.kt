package com.gerosprime.walletexamapp.data

import com.gerosprime.walletexamapp.data.http.FakeTransactionHistoryService
import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import retrofit2.HttpException

class DefaultTransactionHistoryRepositoryTest {

    private lateinit var subject: TransactionHistoryRepository

    private lateinit var fakeWebService: FakeTransactionHistoryService

    @Before
    fun setUp() {
        fakeWebService = FakeTransactionHistoryService()
        subject = DefaultTransactionHistoryRepository(fakeWebService)
    }

    @Test
    fun loadHistory_success_callsWebServiceAndReturnsResponse() {
        fakeWebService.apply {
            mockError = false
            mockResponse = TransactionHistoryResponse(listOf())
        }
        val testObserver = TestObserver<TransactionHistoryResponse>()
        subject.loadHistory().subscribe(testObserver)
        with(testObserver) {
            assertValueCount(1)
            assertNoErrors()
        }
    }

    @Test
    fun loadHistory_error_callsWebServiceAndReturnsError() {
        fakeWebService.apply {
            mockError = true
        }
        val testObserver = TestObserver<TransactionHistoryResponse>()
        subject.loadHistory().subscribe(testObserver)
        with(testObserver) {
            assertNoValues()
            assertError(HttpException::class.java)
        }
    }
}