package com.gerosprime.walletexamapp.data.http.response

class ApiError(override val message: String?) : RuntimeException(message)