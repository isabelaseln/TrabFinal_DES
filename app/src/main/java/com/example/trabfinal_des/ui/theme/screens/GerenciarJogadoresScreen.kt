
package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trabfinal_des.data.local.JogadorEntity
import com.example.trabfinal_des.viewmodel.JogadorUiState

@Composable
fun GerenciarJogadoresScreen(
    uiState: JogadorUiState,
    onCadastrar: (String, String) -> Unit,
    onAtualizar: (JogadorEntity) -> Unit,
    onExcluir: (String) -> Unit,
    onVoltar: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var apelido by remember { mutableStateOf("") }

    var jogadorEditando by remember {
        mutableStateOf<JogadorEntity?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Gerenciar jogadores",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do jogador") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = apelido,
            onValueChange = { apelido = it },
            label = { Text("Apelido") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val jogador = jogadorEditando

                if (jogador == null) {
                    onCadastrar(nome, apelido)
                } else {
                    onAtualizar(
                        jogador.copy(
                            nome = nome,
                            apelido = apelido
                        )
                    )
                }

                nome = ""
                apelido = ""
                jogadorEditando = null
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (jogadorEditando == null) {
                    "Cadastrar jogador"
                } else {
                    "Salvar alterações"
                }
            )
        }

        uiState.mensagem?.let {
            Text(it, color = MaterialTheme.colorScheme.primary)
        }

        uiState.erro?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Jogadores cadastrados",
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.jogadores, key = { it.id }) { jogador ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(jogador.nome)
                        Text("Apelido: ${jogador.apelido}")

                        Row {
                            TextButton(
                                onClick = {
                                    jogadorEditando = jogador
                                    nome = jogador.nome
                                    apelido = jogador.apelido
                                }
                            ) {
                                Text("Editar")
                            }

                            TextButton(
                                onClick = { onExcluir(jogador.id) }
                            ) {
                                Text("Excluir")
                            }
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
