package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.acertenumero.logic.ResultadoJogo

@Composable
fun ResultadoScreen(
    resultado: ResultadoJogo?,
    onVoltarDesafios: () -> Unit,
    onVoltarInicio: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF151B2B))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = if (resultado?.acertou == true) {
                "PARABÉNS!"
            } else {
                "RESULTADO"
            },
            color = Color(0xFF64B5F6),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (resultado != null) {

            Text(
                text = "Você descobriu o número secreto!",
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Número: ${resultado.numeroSecreto}",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Tentativas: ${resultado.tentativas}",
                color = Color.LightGray
            )

        } else {

            Text(
                text = "Nenhum resultado disponível.",
                color = Color.LightGray
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onVoltarDesafios,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("JOGAR NOVAMENTE")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onVoltarInicio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("VOLTAR AO INÍCIO")
        }
    }
}