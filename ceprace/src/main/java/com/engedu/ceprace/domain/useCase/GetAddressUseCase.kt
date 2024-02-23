package com.example.cepraceapp.domain.useCase

import com.engedu.ceprace.domain.model.AddressVO

interface GetAddressUseCase {
    suspend fun execute(cep: String): AddressVO?
}