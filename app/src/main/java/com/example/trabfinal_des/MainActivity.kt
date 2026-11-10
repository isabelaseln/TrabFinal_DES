
package com.example.acertenumeros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AcerteONumeroApp()
            }
        }
    }
}

@Composable
fun AcerteONumeroApp() {

    var perfil by remember { mutableStateOf("inicio") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "ACERTE O NÚMERO",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        when (perfil) {

            "inicio" -> {
                Text("Escolha seu perfil")

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { perfil = "jogador" },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("JOGADOR")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { perfil = "admin" },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("ADMINISTRADOR")
                }
            }

            "jogador" -> {
                Text("Bem-vindo, Jogador!")

                Spacer(modifier = Modifier.height(20.dp))

                Text("Aqui ficará o menu do jogo.")

                Button(onClick = { perfil = "inicio" }) {
                    Text("Voltar")
                }
            }

            "admin" -> {
                Text("Painel do Administrador")

                Spacer(modifier = Modifier.height(20.dp))

                Text("Aqui ficará o gerenciamento do jogo.")

                Button(onClick = { perfil = "inicio" }) {
                    Text("Voltar")
                }
            }
        }
    }
}
