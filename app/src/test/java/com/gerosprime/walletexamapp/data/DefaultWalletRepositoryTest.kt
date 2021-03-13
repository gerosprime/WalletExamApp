package com.gerosprime.walletexamapp.data

import com.gerosprime.walletexamapp.data.http.FakeWalletsWebService
import com.gerosprime.walletexamapp.data.http.response.WalletLoadResponse
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class DefaultWalletRepositoryTest {

    private lateinit var subject: WalletRepository
    private lateinit var fakeWebService: FakeWalletsWebService

    @Before
    fun setUp() {
        fakeWebService = FakeWalletsWebService()
        subject = DefaultWalletRepository(fakeWebService)
    }

    @Test
    fun loadWallets_success_callsWebServiceReturnsResponse() {
        fakeWebService.mockError = false
        fakeWebService.mockResponse = WalletLoadResponse(listOf())
        val testObserver = TestObserver<WalletLoadResponse>()
        subject.loadWallets().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }

    @Test
    fun loadWallets_error_callsWebServiceReturnsError() {
        fakeWebService.mockError = true
        val testObserver = TestObserver<WalletLoadResponse>()
        subject.loadWallets().subscribe(testObserver)
        testObserver.assertNoValues()
        testObserver.assertError(HttpException::class.java)
    }

}