package com.engedu.ceprace.initializer

import com.engedu.ceprace.domain.model.AddressVO

interface CepRaceInit {
    suspend fun execute(cep: String): AddressVO?
}