package com.engedu.ceprace.domain.model

import com.engedu.ceprace.data.model.OpenCepDTO
import com.engedu.ceprace.data.model.PostmonDTO
import com.engedu.ceprace.data.model.ViaCepDTO
import com.engedu.ceprace.data.model.WidnetDTO

internal fun ViaCepDTO.toVo() =
    AddressVO("VIA CEP", this.logradouro, this.bairro, this.cidade, this.estado)

internal fun OpenCepDTO.toVo() =
    AddressVO("OPEN CEP", this.logradouro, this.bairro, this.cidade, this.estado)

internal fun PostmonDTO.toVo() =
    AddressVO("POSTMOM", this.logradouro, this.bairro, this.cidade, this.estadoInfo.nome)

internal fun WidnetDTO.toVo() = AddressVO("WIDNET", this.logradouro, this.bairro, this.cidade, this.estado)