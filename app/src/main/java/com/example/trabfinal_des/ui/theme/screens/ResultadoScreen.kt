
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trabfinal_des.logic.ResultadoJogo

@Composable
fun ResultadoScreen(
    resultado: ResultadoJogo?,
    onVoltarDesafios: () -> Unit,
    onVoltarInicio: () -> Unit
) {
    val azulFundo = Color(0xFF102A43)
    val azulCard = Color(0xFF1B3A57)
    val azulVivo = Color(0xFF3978F6)
    val verde = Color(0xFF20C997)
    val amarelo = Color(0xFFFFC857)
    val branco = Color.White
    val cinza = Color(0xFFB8C7D9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(azulFundo)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "RESULTADO DA PARTIDA",
            color = branco,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (resultado != null) {

            Text(
                text = if (resultado.acertou) "PARABÉNS!" else "FIM DE JOGO",
                color = if (resultado.acertou) verde else amarelo,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = if (resultado.acertou)
                    "Você descobriu o número secreto!"
                else
                    "A partida foi finalizada.",
                color = cinza,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = azulCard
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "NÚMERO SECRETO",
                        color = cinza,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = resultado.numeroSecreto.toString(),
                        color = amarelo,
                        fontSize = 52.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    HorizontalDivider(color = Color(0xFF34536F))

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "TENTATIVAS",
                        color = cinza,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = resultado.tentativas.toString(),
                        color = branco,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            Text(
                text = "Nenhum resultado disponível.",
                color = cinza,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onVoltarDesafios,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = azulVivo
            )
        ) {
            Text(
                text = "JOGAR NOVAMENTE",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedButton(
            onClick = onVoltarInicio,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = branco
            )
        ) {
            Text("VOLTAR AO INÍCIO")
        }
    }
}
