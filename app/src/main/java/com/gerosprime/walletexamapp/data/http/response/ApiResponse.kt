package com.gerosprime.walletexamapp.data.http.response

class ApiResponse<T>(val data: T? = null) {

    companion object {
        const val KEY_SERVER_ERROR = "non_field_errors"
    }


    constructor(httpErrorCode: Int, error : Throwable) : this() {
        this.httpErrorCode = httpErrorCode
        this.error[KEY_SERVER_ERROR] = ApiError(error.localizedMessage)
        this._status = ApiStatus.ERROR
    }

    private var _status: ApiStatus = ApiStatus.SUCCESS
    val status: ApiStatus get() = _status
    private var error: MutableMap<String, ApiError> = mutableMapOf()

    val mainApiError: ApiError get() = error[KEY_SERVER_ERROR]!!

    private var httpErrorCode = 200
    val httpCode: Int get() = httpErrorCode

}