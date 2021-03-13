package com.gerosprime.walletexamapp.data.http

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FakeCall<T> : Call<T> {

    var mockError = false
    var mockResult: T? = null

    override fun clone(): Call<T> {
        TODO("Not yet implemented")
    }

    override fun execute(): Response<T> {
        return if (mockError) {
            Response.error(404, ResponseBody.create(null, ""))
        } else {
            Response.success(mockResult)
        }
    }

    override fun enqueue(callback: Callback<T>) {
        TODO("Not yet implemented")
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }

    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }
}