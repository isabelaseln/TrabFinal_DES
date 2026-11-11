package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.trabfinal_des.data.local.JogadorEntity

@Composable
fun SelecionarJogadorScreen(
    jogadores: List<JogadorEntity>,
    onSelecionar: (JogadorEntity) -> Unit,
    onVoltar: () -> Unit,
    onGerenciarJogadores: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF151B2B))
            .padding(20.dp)
    ) {

        TextButton(onClick = onVoltar) {
            Text("← Voltar", color = Color.White)
        }

        Text(
            text = "Selecione seu jogador",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onGerenciarJogadores,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar jogador")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (jogadores.isEmpty()) {

            Text(
                text = "Nenhum jogador cadastrado.",
                color = Color.LightGray
            )

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(
                    items = jogadores,
                    key = { it.id }
                ) { jogador ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF252F45)
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column {
                                Text(
                                    text = jogador.nome,
                                    color = Color.White
                                )

                                Text(
                                    text = "@${jogador.apelido}",
                                    color = Color.LightGray
                                )
                            }

                            Button(
                                onClick = {
                                    onSelecionar(jogador)
                                }
                            ) {
                                Text("Selecionar")
                            }
                        }
                    }
                }
            }
        }
    }
}