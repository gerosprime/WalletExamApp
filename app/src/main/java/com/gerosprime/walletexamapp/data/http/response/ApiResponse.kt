package com.gerosprime.walletexamapp.data.http.response

import java.lang.IllegalStateException

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

    private var httpErrorCode = 200
    val httpCode: Int get() = httpErrorCode

    val mainApiError: ApiError get() {
        if (errors.containsKey(KEY_SERVER_ERROR)) {
            return errors[KEY_SERVER_ERROR] as ApiError
        } else {
            errors.forEach {
                return it.value
            }
        }
        throw IllegalStateException("No such errors")
    }




}