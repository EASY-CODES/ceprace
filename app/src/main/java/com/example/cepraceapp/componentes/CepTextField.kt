package com.example.cepraceapp.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CepTextField(
    cep: String,
    onCepChange: (String) -> Unit
) {
    TextField(
        value = cep,
        onValueChange = {
            // Verifica se o valor do CEP tem no máximo 8 dígitos e contém apenas números
            if (it.length <= 8 && it.all { char -> char.isDigit() }) {
                onCepChange(it)
            }
        },
        label = { Text("CEP") },
        //keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = cep.isEmpty() || cep.length != 8, // Verifica se o CEP está vazio ou tem comprimento incorreto
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}