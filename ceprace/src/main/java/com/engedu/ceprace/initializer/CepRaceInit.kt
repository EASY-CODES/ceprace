package com.engedu.ceprace.initializer

import com.engedu.ceprace.domain.useCase.GetAddressUseCaseImpl
import com.example.cepraceapp.domain.useCase.GetAddressUseCase
import org.koin.java.KoinJavaComponent.get


object CepRaceInit {
    private val useCase: GetAddressUseCase = get(GetAddressUseCaseImpl::class.java)
    suspend fun execute(cep: String) = useCase.execute(cep)
}