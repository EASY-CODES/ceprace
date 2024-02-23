package com.example.cepraceapp.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun CepButton(
    cep: String,
    onClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Button(
        onClick = {
            // Esconder o teclado quando o botão é clicado
            focusManager.clearFocus()
            onClick()
        },
        modifier = Modifier.fillMaxWidth(),
        enabled = cep.length == 8 && cep.all { char -> char.isDigit() }
    ) {
        Text("Buscar Endereço")
    }
}