package com.example.trabfinal_des.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Fundo = Color(0xFF151B2B)
private val Azul = Color(0xFF3978F6)

@Composable
fun InicioScreen(
    onJogadorClick: () -> Unit,
    onAdminClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fundo)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "ACERTE O NÚMERO",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Será que você consegue descobrir?",
            color = Color.LightGray,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onJogadorClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Azul
            )
        ) {
            Text(
                text = "MODO JOGADOR",
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onAdminClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White
            )
        ) {
            Text(
                text = "MODO ADMINISTRADOR",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}