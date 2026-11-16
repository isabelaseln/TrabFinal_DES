
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
import androidx.compose.material3.FilterChip
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
import com.example.trabfinal_des.data.local.DesafioEntity
import com.example.trabfinal_des.viewmodel.AdminUiState

@Composable
fun GerenciarDesafiosScreen(
    uiState: AdminUiState,
    onCadastrar: (String, Int, Int, Int, String) -> Unit,
    onAtualizar: (DesafioEntity) -> Unit,
    onExcluir: (String) -> Unit,
    onVoltar: () -> Unit
) {
    val azulFundo = Color(0xFF102A43)
    val azulCard = Color(0xFF1B3A57)
    val azulVivo = Color(0xFF3978F6)
    val verde = Color(0xFF20C997)
    val amarelo = Color(0xFFFFC857)
    val coral = Color(0xFFFF6B6B)
    val roxo = Color(0xFFB388FF)
    val branco = Color.White
    val cinza = Color(0xFFB8C7D9)

    var titulo by remember { mutableStateOf("") }
    var minimo by remember { mutableStateOf("1") }
    var maximo by remember { mutableStateOf("100") }
    var secreto by remember { mutableStateOf("84") }
    var dificuldade by remember { mutableStateOf("Fácil") }
    var desafioEditando by remember {
        mutableStateOf<DesafioEntity?>(null)
    }

    fun limparFormulario() {
        titulo = ""
        minimo = "1"
        maximo = "100"
        secreto = "84"
        dificuldade = "Fácil"
        desafioEditando = null
    }

    // Uma única LazyColumn faz a tela inteira rolar.
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
                    text = "GERENCIAR DESAFIOS",
                    color = branco,
                    fontSize = 25.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Cadastre, edite ou exclua os desafios do jogo.",
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
                        text = if (desafioEditando == null)
                            "Cadastrar desafio"
                        else
                            "Editar desafio",
                        color = branco,
                        fontSize = 20.sp
                    )

                    OutlinedTextField(
                        value = titulo,
                        onValueChange = { titulo = it },
                        label = { Text("Nome do desafio") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = minimo,
                        onValueChange = { minimo = it },
                        label = { Text("Número mínimo") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = maximo,
                        onValueChange = { maximo = it },
                        label = { Text("Número máximo") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = secreto,
                        onValueChange = { secreto = it },
                        label = { Text("Número secreto") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Text(
                        text = "Dificuldade",
                        color = branco,
                        fontSize = 15.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(
                            "Fácil",
                            "Médio",
                            "Difícil",
                            "Extremo"
                        ).forEach { opcao ->
                            FilterChip(
                                selected = dificuldade == opcao,
                                onClick = {
                                    dificuldade = opcao
                                },
                                label = {
                                    Text(opcao)
                                }
                            )
                        }
                    }

                    Button(
                        onClick = {
                            val min = minimo.toIntOrNull()
                            val max = maximo.toIntOrNull()
                            val numSecreto = secreto.toIntOrNull()

                            if (
                                titulo.isNotBlank() &&
                                min != null &&
                                max != null &&
                                numSecreto != null
                            ) {
                                val desafio = desafioEditando

                                if (desafio == null) {
                                    onCadastrar(
                                        titulo,
                                        min,
                                        max,
                                        numSecreto,
                                        dificuldade
                                    )
                                } else {
                                    onAtualizar(
                                        desafio.copy(
                                            titulo = titulo,
                                            numeroMinimo = min,
                                            numeroMaximo = max,
                                            numeroSecreto = numSecreto,
                                            dificuldade = dificuldade
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
                            if (desafioEditando == null)
                                "Cadastrar desafio"
                            else
                                "Salvar alterações"
                        )
                    }

                    if (desafioEditando != null) {
                        OutlinedButton(
                            onClick = {
                                limparFormulario()
                            },
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
                text = "Desafios cadastrados",
                color = branco,
                fontSize = 21.sp
            )
        }

        if (uiState.desafios.isEmpty()) {
            item {
                Text(
                    text = "Nenhum desafio cadastrado.",
                    color = cinza,
                    fontSize = 14.sp
                )
            }
        }

        items(
            items = uiState.desafios,
            key = { it.id }
        ) { desafio ->

            val dificuldadeNormalizada = desafio.dificuldade
                .trim()
                .uppercase()
                .replace("Á", "A")
                .replace("É", "E")
                .replace("Í", "I")

            val corDificuldade = when (dificuldadeNormalizada) {
                "FACIL" -> verde
                "MEDIO" -> amarelo
                "DIFICIL" -> coral
                "EXTREMO" -> roxo
                else -> azulVivo
            }

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
                        text = desafio.titulo,
                        color = branco,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "Intervalo: ${desafio.numeroMinimo} até ${desafio.numeroMaximo}",
                        color = cinza,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "Número secreto: ${desafio.numeroSecreto}",
                        color = amarelo,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "Dificuldade: ${desafio.dificuldade}",
                        color = corDificuldade,
                        fontSize = 14.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {
                                desafioEditando = desafio
                                titulo = desafio.titulo
                                minimo = desafio.numeroMinimo.toString()
                                maximo = desafio.numeroMaximo.toString()
                                secreto = desafio.numeroSecreto.toString()
                                dificuldade = desafio.dificuldade
                            }
                        ) {
                            Text(
                                text = "Editar",
                                color = amarelo
                            )
                        }

                        TextButton(
                            onClick = {
                                onExcluir(desafio.id)
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
