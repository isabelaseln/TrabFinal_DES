
package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trabfinal_des.data.local.JogadorEntity

@Composable
fun SelecionarJogadorScreen(
    jogadores: List<JogadorEntity>,
    onSelecionar: () -> Unit,
    onVoltar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Quem vai jogar?",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (jogadores.isEmpty()) {
            Text("Nenhum jogador cadastrado.")
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(jogadores, key = { it.id }) { jogador ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    onClick = onSelecionar
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(jogador.nome)
                        Text("Apelido: ${jogador.apelido}")
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
