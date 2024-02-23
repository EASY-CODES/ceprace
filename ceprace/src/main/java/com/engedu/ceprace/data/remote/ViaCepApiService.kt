package com.engedu.ceprace.data.remote

import com.engedu.ceprace.data.model.ViaCepDTO
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ViaCepApiService {
    @GET("/ws/{cep}/json/")
    suspend fun getAddress(@Path("cep") cep: String): ViaCepDTO
}
