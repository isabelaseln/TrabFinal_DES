
package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

    val azulFundo = Color(0xFF102A43)
    val azulCard = Color(0xFF1B3A57)
    val azulVivo = Color(0xFF3978F6)
    val branco = Color.White
    val cinza = Color(0xFFB8C7D9)
    val verde = Color(0xFF20C997)
    val amarelo = Color(0xFFFFC857)

    val corMensagem = when {
        uiState.erro != null -> Color(0xFFFF6B6B)
        uiState.partidaFinalizada -> verde
        else -> amarelo
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(azulFundo)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        Text(
            text = desafio?.titulo ?: "Acerte o Número",
            style = MaterialTheme.typography.headlineMedium,
            color = branco,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Descubra qual é o número secreto!",
            color = cinza,
            style = MaterialTheme.typography.bodyMedium
        )

        // Painel dos limites
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = azulCard
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "O número está entre",
                    color = cinza
                )

                Spacer(modifier = Modifier.height(10.dp))

                if (desafio != null) {
                    Text(
                        text = "${uiState.limiteMinimo} < ? < ${uiState.limiteMaximo}",
                        style = MaterialTheme.typography.headlineMedium,
                        color = amarelo,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Use as dicas para chegar à resposta!",
                    color = cinza,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        // Tentativas
        Surface(
            color = azulCard,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Tentativas: ${uiState.tentativas}",
                color = branco,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    horizontal = 22.dp,
                    vertical = 12.dp
                )
            )
        }

        // Campo de palpite
        OutlinedTextField(
            value = uiState.palpite,
            onValueChange = onPalpiteChange,
            label = { Text("Digite seu palpite") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.partidaFinalizada,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = branco,
                unfocusedTextColor = branco,
                focusedBorderColor = azulVivo,
                unfocusedBorderColor = cinza,
                focusedLabelColor = azulVivo,
                unfocusedLabelColor = cinza,
                cursorColor = azulVivo
            )
        )

        // Botão enviar
        Button(
            onClick = onEnviarPalpite,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = !uiState.partidaFinalizada,
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = azulVivo,
                contentColor = branco
            )
        ) {
            Text(
                text = "Enviar palpite",
                fontWeight = FontWeight.Bold
            )
        }

        // Mensagem do jogo
        if (uiState.mensagem.isNotBlank()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = azulCard
                )
            ) {
                Text(
                    text = uiState.mensagem,
                    color = corMensagem,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Erros
        uiState.erro?.let {
            Text(
                text = it,
                color = Color(0xFFFF6B6B),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Resultado
        if (uiState.partidaFinalizada) {
            Button(
                onClick = onVerResultado,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = verde,
                    contentColor = Color(0xFF263746)
                )
            ) {
                Text(
                    text = "Ver resultado",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = onVoltar,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = branco
            )
        ) {
            Text("Voltar aos desafios")
        }
    }
}
