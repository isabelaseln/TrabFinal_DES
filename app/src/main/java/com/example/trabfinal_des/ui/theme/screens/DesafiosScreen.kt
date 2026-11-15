
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
import androidx.compose.ui.unit.dp
import com.example.trabfinal_des.data.local.DesafioEntity

@Composable
fun DesafiosScreen(
    desafios: List<DesafioEntity>,
    onSelecionar: (DesafioEntity) -> Unit,
    onVoltar: () -> Unit
) {
    val azulFundo = Color(0xFF102A43)
    val azulCard = Color(0xFF1B3A57)
    val azulVivo = Color(0xFF3978F6)
    val branco = Color.White
    val cinza = Color(0xFFB8C7D9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(azulFundo)
            .padding(20.dp)
    ) {

        Text(
            text = "Escolha seu desafio",
            style = MaterialTheme.typography.headlineMedium,
            color = branco
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Teste suas habilidades e descubra o número secreto!",
            color = cinza,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (desafios.isEmpty()) {
            Text(
                text = "Não existem desafios disponíveis.",
                color = branco
            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(desafios, key = { it.id }) { desafio ->

                val corDificuldade = when (
                    desafio.dificuldade.uppercase()
                ) {
                    "FÁCIL", "FACIL" -> Color(0xFF20C997)
                    "MÉDIA", "MEDIA" -> Color(0xFFFFC857)
                    "MÁXIMA", "MÁXIMA" -> Color(0xFFFF6B6B)
                    else -> azulVivo
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = azulCard
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = desafio.titulo,
                                style = MaterialTheme.typography.titleLarge,
                                color = branco
                            )

                            Surface(
                                color = corDificuldade,
                                shape = RoundedCornerShape(50.dp)
                            ) {
                                Text(
                                    text = desafio.dificuldade,
                                    color = Color(0xFF263746),
                                    style = MaterialTheme.typography.labelLarge,
                                    modifier = Modifier.padding(
                                        horizontal = 12.dp,
                                        vertical = 6.dp
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Descubra o número secreto!",
                            color = cinza
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "${desafio.numeroMinimo} até ${desafio.numeroMaximo}",
                            color = branco,
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Button(
                            onClick = { onSelecionar(desafio) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = azulVivo,
                                contentColor = branco
                            )
                        ) {
                            Text("Jogar este desafio")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onVoltar,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = branco
            )
        ) {
            Text("Voltar")
        }
    }
}
