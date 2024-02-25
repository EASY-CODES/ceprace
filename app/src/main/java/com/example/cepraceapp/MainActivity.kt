package com.example.cepraceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.engedu.ceprace.domain.model.AddressVO
import com.engedu.ceprace.initializer.CepRaceInitImpl
import com.example.cepraceapp.componentes.AppTitle
import com.example.cepraceapp.componentes.CepButton
import com.example.cepraceapp.componentes.CepTextField
import com.example.cepraceapp.componentes.RunnersList
import com.example.cepraceapp.componentes.WinnerText
import com.example.cepraceapp.util.ViewState
import com.example.cepraceapp.theme.CepRaceAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.newFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CepRaceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(viewModel)
                }
            }
        }
    }

    @Composable
    fun MainView(mainViewModel: MainViewModel) {

        // Estado do campo de texto para o CEP
        var cep by remember { mutableStateOf("") }

        // Coletando o estado do ViewModel
        val addressState by mainViewModel.addressState.collectAsState()

        // Composable principal
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppTitle()

            RunnersList()

            CepTextField(cep = cep, onCepChange = { newCep -> cep = newCep })

            Spacer(modifier = Modifier.height(16.dp))

            CepButton(cep = cep, onClick = { mainViewModel.getAddress(cep) })

            Spacer(modifier = Modifier.height(16.dp))

            // Manipulação dos estados
            when (addressState) {
                is ViewState.Idle -> {
                    // Parado, inativo
                }

                is ViewState.Loading -> {
                    // Exibindo tela de carregamento
                    CircularProgressIndicator()
                }

                is ViewState.Success -> {
                    // Exibindo os dados do endereço
                    val data = (addressState as ViewState.Success<AddressVO>).data
                    WinnerText(winnerName = data.service)
                    Text("${data.logradouro}, ${data.bairro}, ${data.cidade}, ${data.estado}")
                }

                is ViewState.Error -> {
                    // Exibindo mensagem de erro
                    val error = (addressState as ViewState.Error).error
                    Text("Erro: $error")
                }
            }
        }
    }
}

