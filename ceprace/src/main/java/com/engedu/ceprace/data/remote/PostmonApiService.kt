package com.engedu.ceprace.data.remote

import com.engedu.ceprace.data.model.PostmonDTO
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PostmonApiService {
    @GET("{cep}")
    suspend fun getAddress(@Path("cep") cep: String): PostmonDTO
}
