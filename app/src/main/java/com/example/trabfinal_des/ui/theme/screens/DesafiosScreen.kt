
package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trabfinal_des.data.local.DesafioEntity

@Composable
fun DesafiosScreen(
    desafios: List<DesafioEntity>,
    onSelecionar: (DesafioEntity) -> Unit,
    onVoltar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Escolha um desafio",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (desafios.isEmpty()) {
            Text("Não existem desafios disponíveis.")
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(desafios, key = { it.id }) { desafio ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            desafio.titulo,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text("Dificuldade: ${desafio.dificuldade}")

                        Text(
                            "Descubra um número entre ${desafio.numeroMinimo} e ${desafio.numeroMaximo}."
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = { onSelecionar(desafio) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Jogar este desafio")
                        }
                    }
                }
            }
        }

        OutlinedButton(
            onClick = onVoltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
