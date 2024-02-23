package com.engedu.ceprace.domain.model

internal data class Response(
    val success: Boolean = false,
    val addressVO: AddressVO? = null,
    val errorMsg: String? = null
)