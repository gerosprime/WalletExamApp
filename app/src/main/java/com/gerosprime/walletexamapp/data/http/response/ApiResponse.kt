package com.gerosprime.walletexamapp.data.http.response

class ApiResponse<T>(val data: T? = null) {

    companion object {
        const val KEY_SERVER_ERROR = "non_field_errors"
    }


    constructor(httpErrorCode: Int, error : Throwable) : this() {
        this.httpErrorCode = httpErrorCode
        this.errors[KEY_SERVER_ERROR] = ApiError(error.localizedMessage)
        this.status = ApiStatus.ERROR
    }

    private var status: ApiStatus = ApiStatus.SUCCESS
    val responseStatus: ApiStatus get() = status
    private var errors: MutableMap<String, ApiError> = mutableMapOf()

    val mainApiError: ApiError get() = errors[KEY_SERVER_ERROR]!!

    private var httpErrorCode = 200
    val httpCode: Int get() = httpErrorCode

}