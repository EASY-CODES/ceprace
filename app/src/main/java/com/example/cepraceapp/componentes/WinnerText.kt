package com.example.cepraceapp.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cepraceapp.R

@Composable
fun WinnerText(winnerName: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = R.drawable.ic_winner), // substitua R.drawable.ic_winner pelo seu ícone
            contentDescription = "Ícone do vencedor",
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = winnerName,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.width(8.dp))
    }
}