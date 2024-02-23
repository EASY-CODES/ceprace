package com.engedu.ceprace.data.model

import com.google.gson.annotations.SerializedName

internal data class ViaCepDTO(
    @SerializedName("logradouro") val logradouro: String,
    @SerializedName("bairro") val bairro: String,
    @SerializedName("localidade") val cidade: String,
    @SerializedName("uf") val estado: String
)