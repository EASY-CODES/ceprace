package com.example.cepraceapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engedu.ceprace.initializer.CepRaceInit
import com.engedu.ceprace.domain.model.AddressVO
import com.example.cepraceapp.util.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private lateinit var _cep: String
    private val _addressState = MutableStateFlow<ViewState<AddressVO>>(ViewState.Idle)
    val addressState: StateFlow<ViewState<AddressVO>> = _addressState


    fun getAddress(cep: String) {
        _cep = cep
        viewModelScope.launch {
            try {
                _addressState.value = ViewState.Loading
                CepRaceInit.execute(_cep)?.let {
                    _addressState.value = ViewState.Success(it)
                }

            } catch (e: Exception) {
                _addressState.value =
                    ViewState.Error("Parece que ninguém cruzou a linha de chagada =(", ::tryAgain)
            }
        }
    }

    private fun tryAgain() {
        getAddress(_cep)
    }
}