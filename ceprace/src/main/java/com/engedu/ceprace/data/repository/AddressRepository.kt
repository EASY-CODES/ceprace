package com.engedu.ceprace.data.repository

import com.engedu.ceprace.data.model.OpenCepDTO
import com.engedu.ceprace.data.model.PostmonDTO
import com.engedu.ceprace.data.model.ViaCepDTO
import com.engedu.ceprace.data.model.WidnetDTO

internal interface AddressRepository {
    suspend fun getAddressViaCep(cep: String): ViaCepDTO
    suspend fun getAddressPostmon(cep: String): PostmonDTO
    suspend fun getAddressOpenCep(cep: String): OpenCepDTO
    suspend fun getAddressWidnet(cep: String): WidnetDTO
}