package com.gerosprime.walletexamapp.data.http.response

import com.gerosprime.walletexamapp.domain.Transaction

class TransactionHistoryResponse(val histories: List<Transaction>,
                                 val errors: ResponseErrors
)