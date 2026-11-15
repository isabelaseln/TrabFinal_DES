
package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trabfinal_des.viewmodel.JogoUiState

@Composable
fun JogoScreen(
    uiState: JogoUiState,
    onPalpiteChange: (String) -> Unit,
    onEnviarPalpite: () -> Unit,
    onVoltar: () -> Unit,
    onVerResultado: () -> Unit
) {
    val desafio = uiState.desafioSelecionado

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            desafio?.titulo ?: "Acerte o Número",
            style = MaterialTheme.typography.headlineMedium
        )

        if (desafio != null) {
            Text(
                "${uiState.limiteMinimo} < número secreto < ${uiState.limiteMaximo}",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Text("Tentativas: ${uiState.tentativas}")

        OutlinedTextField(
            value = uiState.palpite,
            onValueChange = onPalpiteChange,
            label = { Text("Digite seu palpite") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.partidaFinalizada
        )

        Button(
            onClick = onEnviarPalpite,
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.partidaFinalizada
        ) {
            Text("Enviar palpite")
        }

        Text(
            uiState.mensagem,
            style = MaterialTheme.typography.titleMedium
        )

        uiState.erro?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        if (uiState.partidaFinalizada) {
            Button(
                onClick = onVerResultado,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver resultado")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = onVoltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar aos desafios")
        }
    }
}
