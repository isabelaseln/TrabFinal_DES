
package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trabfinal_des.logic.ResultadoJogo

@Composable
fun ResultadoScreen(
    resultado: ResultadoJogo?,
    onVoltarDesafios: () -> Unit,
    onVoltarInicio: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Resultado da partida",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (resultado != null) {
            Text(
                if (resultado.acertou) "Parabéns! Você acertou!"
                else "Partida finalizada"
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Número secreto: ${resultado.numeroSecreto}")

            Text("Tentativas: ${resultado.tentativas}")
        } else {
            Text("Nenhum resultado disponível.")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onVoltarDesafios,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Jogar novamente")
        }

        OutlinedButton(
            onClick = onVoltarInicio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar ao início")
        }
    }
}
