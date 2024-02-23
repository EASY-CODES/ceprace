package com.engedu.ceprace.data.remote

import com.engedu.ceprace.data.model.OpenCepDTO
import retrofit2.http.GET
import retrofit2.http.Path

internal interface OpenCepApiService {
    @GET("{cep}")
    suspend fun getAddress(@Path("cep") cep: String): OpenCepDTO
}
