package com.engedu.ceprace.domain.useCase

import com.engedu.ceprace.data.repository.AddressRepository
import com.engedu.ceprace.domain.model.AddressVO
import com.engedu.ceprace.domain.model.Response
import com.engedu.ceprace.domain.model.toVo
import com.example.cepraceapp.domain.useCase.GetAddressUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map


internal class GetAddressUseCaseImpl(private val repository: AddressRepository) :
    GetAddressUseCase {
    override suspend fun execute(cep: String): AddressVO? {
        return ranceFlow(cep).first()
    }

    @OptIn(FlowPreview::class)
    private suspend fun ranceFlow(cep: String) = flow {

        val viaCepFlow = flow {
            emit(Response(success = true, addressVO = repository.getAddressViaCep(cep).toVo()))
        }.catch { emit(Response(errorMsg = it.message)) }

        val openCepFlow = flow {
            emit(Response(success = true, addressVO = repository.getAddressOpenCep(cep).toVo()))
        }.catch { emit(Response(errorMsg = it.message)) }

        val postmonFlow = flow {
            emit(Response(success = true, addressVO = repository.getAddressPostmon(cep).toVo()))
        }.catch { emit(Response(errorMsg = it.message)) }

        val widnetFlow = flow {
            emit(Response(success = true, addressVO = repository.getAddressWidnet(cep).toVo()))
        }.catch { emit(Response(errorMsg = it.message)) }

        /**
         * flatMapMerge é um operador do Kotlin Flow que permite combinar vários fluxos em um único fluxo,
         * onde os elementos dos fluxos originais são emitidos em ordem, mas as emissões ocorrem concorrentemente.
         *
         * Quando você aplica flatMapMerge a um fluxo de entrada, ele aceita um lambda que mapeia cada elemento do
         * fluxo para um novo fluxo. Em seguida, mescla todos esses fluxos resultantes em um único fluxo, emitindo
         * os elementos à medida que estão prontos, independentemente da ordem de chegada dos elementos originais.
         */
        val combinedFlow = flowOf(viaCepFlow, openCepFlow, postmonFlow, widnetFlow)
            .flatMapMerge { it }
            .filter { it.success }
            .map { it.addressVO }

        combinedFlow.collect {
            emit(it)
        }
    }

}