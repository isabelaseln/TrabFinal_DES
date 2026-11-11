package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.acertenumero.data.local.JogadorEntity
import com.example.acertenumero.viewmodel.JogadorUiState

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
            .background(Color(0xFF151B2B))
            .padding(20.dp)
    ) {

        TextButton(onClick = onVoltar) {
            Text("← Voltar", color = Color.White)
        }

        Text(
            text = "Gerenciar jogadores",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

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

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                val existente = jogadorEditando

                if (existente == null) {

                    onCadastrar(nome, apelido)

                } else {

                    onAtualizar(
                        existente.copy(
                            nome = nome.trim(),
                            apelido = apelido.trim()
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
                    "CADASTRAR JOGADOR"
                } else {
                    "SALVAR ALTERAÇÕES"
                }
            )
        }

        TextButton(
            onClick = {
                nome = ""
                apelido = ""
                jogadorEditando = null
            }
        ) {
            Text("Limpar formulário")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Jogadores cadastrados",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(
                items = uiState.jogadores,
                key = { it.id }
            ) { jogador ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF252F45)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {

                        Text(
                            text = jogador.nome,
                            color = Color.White
                        )

                        Text(
                            text = "@${jogador.apelido}",
                            color = Color.LightGray
                        )

                        Row {

                            TextButton(
                                onClick = {
                                    jogadorEditando = jogador
                                    nome = jogador.nome
                                    apelido = jogador.apelido
                                }
                            ) {
                                Text("EDITAR")
                            }

                            TextButton(
                                onClick = {
                                    onExcluir(jogador.id)

                                    if (jogadorEditando?.id == jogador.id) {
                                        jogadorEditando = null
                                        nome = ""
                                        apelido = ""
                                    }
                                }
                            ) {
                                Text(
                                    "EXCLUIR",
                                    color = Color(0xFFFF7777)
                                )
                            }
                        }
                    }
                }
            }
        }

        uiState.erro?.let {
            Text(it, color = Color(0xFFFF7777))
        }

        uiState.mensagem?.let {
            Text(it, color = Color(0xFF81C784))
        }
    }
}