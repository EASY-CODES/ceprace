package com.engedu.ceprace.data.remote

import com.engedu.ceprace.data.model.WidnetDTO
import retrofit2.http.GET
import retrofit2.http.Path

internal interface WidnetApiService {
    @GET("{cep}.json")
    suspend fun getAddress(@Path("cep") cep: String): WidnetDTO
}
