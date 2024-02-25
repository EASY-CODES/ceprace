package com.engedu.ceprace.data.repository

import com.engedu.ceprace.data.remote.OpenCepApiService
import com.engedu.ceprace.data.remote.PostmonApiService
import com.engedu.ceprace.data.remote.ViaCepApiService
import com.engedu.ceprace.data.remote.WidnetApiService
import kotlinx.coroutines.coroutineScope

internal class AddressRepositoryImpl(
    private val viaCepApiService: ViaCepApiService,
    private val openCepApiService: OpenCepApiService,
    private val postmonApiService: PostmonApiService,
    private val widnetApiService: WidnetApiService

) : AddressRepository {
    override suspend fun getAddressViaCep(cep: String) = coroutineScope {
        viaCepApiService.getAddress(cep)
    }

    override suspend fun getAddressPostmon(cep: String) = coroutineScope {
        postmonApiService.getAddress(cep)
    }

    override suspend fun getAddressOpenCep(cep: String) = coroutineScope {
        openCepApiService.getAddress(cep)
    }

    override suspend fun getAddressWidnet(cep: String) = coroutineScope {
        widnetApiService.getAddress(cep)
    }

}