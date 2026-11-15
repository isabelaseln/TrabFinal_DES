
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
import com.example.trabfinal_des.viewmodel.AdminUiState

@Composable
fun AdminScreen(
    uiState: AdminUiState,
    onSincronizar: () -> Unit,
    onVoltar: () -> Unit
) {
    val azulFundo = Color(0xFF102A43)
    val azulCard = Color(0xFF1B3A57)
    val azulVivo = Color(0xFF3978F6)
    val verde = Color(0xFF20C997)
    val branco = Color.White
    val cinza = Color(0xFFB8C7D9)
    val vermelho = Color(0xFFFF6B6B)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(azulFundo)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = "ÁREA DO ADMINISTRADOR",
            color = branco,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            text = "Gerencie os dados e mantenha os desafios atualizados.",
            color = cinza,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

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
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Sincronização",
                    color = branco,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Atualize os desafios utilizando os dados do Firebase.",
                    color = cinza,
                    fontSize = 14.sp
                )

                Button(
                    onClick = onSincronizar,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    enabled = !uiState.carregando,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = azulVivo
                    )
                ) {
                    Text(
                        text = "Sincronizar desafios",
                        fontWeight = FontWeight.Bold
                    )
                }

                if (uiState.carregando) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(
                            color = verde,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                uiState.mensagem?.let {
                    Text(
                        text = it,
                        color = verde,
                        fontSize = 14.sp
                    )
                }

                uiState.erro?.let {
                    Text(
                        text = it,
                        color = vermelho,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = onVoltar,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = branco
            )
        ) {
            Text("Voltar")
        }
    }
}
