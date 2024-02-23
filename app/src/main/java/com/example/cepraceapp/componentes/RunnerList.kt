package com.example.cepraceapp.componentes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cepraceapp.R

@Composable
fun RunnersList() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        RunnerItem(name = "VIA CEP API")
        RunnerItem(name = "OPEM CEP API")
        RunnerItem(name = "POSTMON API")
        RunnerItem(name = "WIDNET API")
    }
}

@Composable
fun RunnerItem(name: String) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_helmet),
                contentDescription = "Runner",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = name)
        }
    }
}

@Preview
@Composable
fun RunnersListPreview() {
    MaterialTheme {
        RunnersList()
    }
}
