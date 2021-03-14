package com.gerosprime.walletexamapp.data

import com.gerosprime.walletexamapp.data.http.FakeTransactionHistoryService
import com.gerosprime.walletexamapp.data.http.response.ApiError
import com.gerosprime.walletexamapp.data.http.response.TransactionHistoryResponse
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import retrofit2.HttpException

class DefaultTransactionHistoryRepositoryTest {

    private lateinit var subject: DefaultTransactionHistoryRepository

    private lateinit var fakeWebService: FakeTransactionHistoryService

    @Before
    fun setUp() {
        fakeWebService = FakeTransactionHistoryService()
        subject = DefaultTransactionHistoryRepository(fakeWebService)
        subject.enableDelay = false
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

    @Test
    fun loadHistory_apiError_callsWebServiceAndReturnsError() {
        fakeWebService.apply {
            mockApiError = true
        }
        val testObserver = TestObserver<TransactionHistoryResponse>()
        subject.loadHistory().subscribe(testObserver)
        with(testObserver) {
            assertNoValues()
            assertError(ApiError::class.java)
        }
    }
}