
package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trabfinal_des.viewmodel.AdminUiState

@Composable
fun AdminScreen(
    uiState: AdminUiState,
    onSincronizar: () -> Unit,
    onVoltar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Área do Administrador",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedButton(
            onClick = onSincronizar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sincronizar desafios com Firebase")
        }

        if (uiState.carregando) {
            CircularProgressIndicator()
        }

        uiState.mensagem?.let {
            Text(it, color = MaterialTheme.colorScheme.primary)
        }

        uiState.erro?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = onVoltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
