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
import com.example.acertenumero.data.local.DesafioEntity

@Composable
fun DesafiosScreen(
    desafios: List<DesafioEntity>,
    onSelecionar: (DesafioEntity) -> Unit,
    onVoltar: () -> Unit
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
            text = "Escolha seu desafio",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (desafios.isEmpty()) {

            Text(
                text = "Nenhum desafio disponível no momento.",
                color = Color.LightGray
            )

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = desafios,
                    key = { it.id }
                ) { desafio ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF252F45)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = desafio.titulo,
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge
                            )

                            Text(
                                text = "Dificuldade: ${desafio.dificuldade}",
                                color = Color.LightGray
                            )

                            Text(
                                text = "Intervalo: ${desafio.numeroMinimo} até ${desafio.numeroMaximo}",
                                color = Color.LightGray
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = {
                                    onSelecionar(desafio)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("JOGAR")
                            }
                        }
                    }
                }
            }
        }
    }
}