
package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trabfinal_des.data.local.JogadorEntity

@Composable
fun SelecionarJogadorScreen(
    jogadores: List<JogadorEntity>,
    onSelecionar: (JogadorEntity) -> Unit,
    onVoltar: () -> Unit
) {
    val azulFundo = Color(0xFF102A43)
    val azulCard = Color(0xFF1B3A57)
    val azulVivo = Color(0xFF3978F6)
    val branco = Color.White
    val cinza = Color(0xFFB8C7D9)
    val verde = Color(0xFF20C997)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(azulFundo)
            .padding(20.dp)
    ) {
        Text(
            text = "QUEM VAI JOGAR?",
            color = branco,
            fontSize = 27.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Escolha um jogador para começar!",
            color = cinza,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (jogadores.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = azulCard
                )
            ) {
                Text(
                    text = "Nenhum jogador cadastrado.",
                    color = cinza,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(jogadores, key = { it.id }) { jogador ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onSelecionar(jogador)
                    },
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = azulCard
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(48.dp),
                            shape = RoundedCornerShape(14.dp),
                            color = azulVivo
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = jogador.nome
                                        .firstOrNull()
                                        ?.uppercase() ?: "?",
                                    color = branco,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = jogador.nome,
                                color = branco,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Apelido: ${jogador.apelido}",
                                color = cinza,
                                fontSize = 14.sp
                            )
                        }

                        Text(
                            text = "›",
                            color = verde,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
