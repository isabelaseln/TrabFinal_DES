package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
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
            .background(Color(0xFF151B2B))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextButton(onClick = onVoltar) {
            Text("← Voltar", color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = desafio?.titulo ?: "Acerte o Número",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (desafio != null) {
                "${desafio.numeroMinimo} < número secreto < ${desafio.numeroMaximo}"
            } else {
                "Selecione um desafio."
            },
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Qual é o seu palpite?",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.palpite,
            onValueChange = onPalpiteChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = desafio != null && !uiState.partidaFinalizada,
            label = { Text("Digite um número") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = Color(0xFF3978F6),
                unfocusedLabelColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onEnviarPalpite,
            enabled = desafio != null && !uiState.partidaFinalizada,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("TESTAR PALPITE")
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Tentativas: ${uiState.tentativas}",
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (uiState.mensagem.isNotBlank()) {

            Text(
                text = uiState.mensagem,
                color = Color(0xFF64B5F6),
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (uiState.erro != null) {

            Text(
                text = uiState.erro,
                color = Color(0xFFFF7777)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (uiState.partidaFinalizada) {

            Button(
                onClick = onVerResultado,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("VER RESULTADO")
            }
        }
    }
}