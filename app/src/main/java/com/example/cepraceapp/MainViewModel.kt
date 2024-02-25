package com.example.cepraceapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.engedu.ceprace.MyApplication
import com.engedu.ceprace.initializer.CepRaceInitImpl
import com.engedu.ceprace.domain.model.AddressVO
import com.engedu.ceprace.initializer.CepRaceInit
import com.example.cepraceapp.util.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val cepRaceInit: CepRaceInit) : ViewModel() {

    private lateinit var _cep: String
    private val _addressState = MutableStateFlow<ViewState<AddressVO>>(ViewState.Idle)
    val addressState: StateFlow<ViewState<AddressVO>> = _addressState


    fun getAddress(cep: String) {
        _cep = cep
        viewModelScope.launch {
            try {
                _addressState.value = ViewState.Loading
                cepRaceInit.execute(_cep)?.let {
                    _addressState.value = ViewState.Success(it)
                }

            } catch (e: Exception) {
                _addressState.value =
                    ViewState.Error("Parece que ninguém cruzou a linha de chagada =(", ::tryAgain)
            }
        }
    }

    fun tryAgain() {
        getAddress(_cep)
    }

    companion object {
        fun newFactory(cepRaceInit: CepRaceInit = CepRaceInitImpl()) = viewModelFactory {
            initializer {
                MainViewModel(cepRaceInit)
            }

        }
    }
}