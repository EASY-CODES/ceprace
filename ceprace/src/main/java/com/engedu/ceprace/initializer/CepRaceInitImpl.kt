package com.engedu.ceprace.initializer

import com.engedu.ceprace.domain.model.AddressVO
import com.engedu.ceprace.domain.useCase.GetAddressUseCaseImpl
import com.example.cepraceapp.domain.useCase.GetAddressUseCase
import org.koin.java.KoinJavaComponent.get


class CepRaceInitImpl(private val useCaseImpl: GetAddressUseCase = get(GetAddressUseCaseImpl::class.java)) :
    CepRaceInit {
    override suspend fun execute(cep: String) = useCaseImpl.execute(cep)
}