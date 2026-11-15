
package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    var titulo by remember { mutableStateOf("") }
    var minimo by remember { mutableStateOf("1") }
    var maximo by remember { mutableStateOf("100") }
    var secreto by remember { mutableStateOf("84") }
    var dificuldade by remember { mutableStateOf("Fácil") }

    var desafioEditando by remember {
        mutableStateOf<DesafioEntity?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Gerenciar desafios",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título do desafio") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = minimo,
            onValueChange = { minimo = it },
            label = { Text("Número mínimo") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = maximo,
            onValueChange = { maximo = it },
            label = { Text("Número máximo") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = secreto,
            onValueChange = { secreto = it },
            label = { Text("Número secreto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dificuldade,
            onValueChange = { dificuldade = it },
            label = { Text("Dificuldade") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val min = minimo.toIntOrNull()
                val max = maximo.toIntOrNull()
                val numSecreto = secreto.toIntOrNull()

                if (min != null && max != null && numSecreto != null) {
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

                    titulo = ""
                    minimo = "1"
                    maximo = "100"
                    secreto = "84"
                    dificuldade = "Fácil"
                    desafioEditando = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (desafioEditando == null) {
                    "Cadastrar desafio"
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
            "Desafios cadastrados",
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.desafios, key = { it.id }) { desafio ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(desafio.titulo)
                        Text(
                            "Intervalo: ${desafio.numeroMinimo} até ${desafio.numeroMaximo}"
                        )
                        Text("Dificuldade: ${desafio.dificuldade}")

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                                Text("Editar")
                            }

                            TextButton(
                                onClick = { onExcluir(desafio.id) }
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
