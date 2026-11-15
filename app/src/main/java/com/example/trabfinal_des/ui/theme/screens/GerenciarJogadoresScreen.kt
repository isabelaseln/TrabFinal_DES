
package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val azulFundo = Color(0xFF102A43)
    val azulCard = Color(0xFF1B3A57)
    val azulVivo = Color(0xFF3978F6)
    val verde = Color(0xFF20C997)
    val amarelo = Color(0xFFFFC857)
    val coral = Color(0xFFFF6B6B)
    val branco = Color.White
    val cinza = Color(0xFFB8C7D9)

    var nome by remember { mutableStateOf("") }
    var apelido by remember { mutableStateOf("") }
    var jogadorEditando by remember { mutableStateOf<JogadorEntity?>(null) }

    fun limparFormulario() {
        nome = ""
        apelido = ""
        jogadorEditando = null
    }

    // A tela inteira utiliza uma única área de rolagem.
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(azulFundo)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Column {
                Text(
                    text = "GERENCIAR JOGADORES",
                    color = branco,
                    fontSize = 25.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Cadastre, edite ou exclua os jogadores.",
                    color = cinza,
                    fontSize = 14.sp
                )
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = azulCard
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = if (jogadorEditando == null)
                            "Cadastrar jogador"
                        else
                            "Editar jogador",
                        color = branco,
                        fontSize = 20.sp
                    )

                    OutlinedTextField(
                        value = nome,
                        onValueChange = { nome = it },
                        label = { Text("Nome do jogador") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = apelido,
                        onValueChange = { apelido = it },
                        label = { Text("Apelido") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Button(
                        onClick = {
                            if (nome.isNotBlank() && apelido.isNotBlank()) {
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

                                limparFormulario()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = azulVivo
                        )
                    ) {
                        Text(
                            if (jogadorEditando == null)
                                "Cadastrar jogador"
                            else
                                "Salvar alterações"
                        )
                    }

                    if (jogadorEditando != null) {
                        OutlinedButton(
                            onClick = { limparFormulario() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Cancelar edição")
                        }
                    }
                }
            }
        }

        item {
            uiState.mensagem?.let { mensagem ->
                Text(
                    text = mensagem,
                    color = verde,
                    fontSize = 14.sp
                )
            }

            uiState.erro?.let { erro ->
                Text(
                    text = erro,
                    color = coral,
                    fontSize = 14.sp
                )
            }
        }

        item {
            Text(
                text = "Jogadores cadastrados",
                color = branco,
                fontSize = 21.sp
            )
        }

        if (uiState.jogadores.isEmpty()) {
            item {
                Text(
                    text = "Nenhum jogador cadastrado.",
                    color = cinza,
                    fontSize = 14.sp
                )
            }
        }

        items(
            items = uiState.jogadores,
            key = { it.id }
        ) { jogador ->

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = azulCard
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = jogador.nome,
                        color = branco,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "Apelido: ${jogador.apelido}",
                        color = cinza,
                        fontSize = 14.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {
                                jogadorEditando = jogador
                                nome = jogador.nome
                                apelido = jogador.apelido
                            }
                        ) {
                            Text(
                                text = "Editar",
                                color = amarelo
                            )
                        }

                        TextButton(
                            onClick = {
                                onExcluir(jogador.id)
                            }
                        ) {
                            Text(
                                text = "Excluir",
                                color = coral
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedButton(
                onClick = onVoltar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Voltar")
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
