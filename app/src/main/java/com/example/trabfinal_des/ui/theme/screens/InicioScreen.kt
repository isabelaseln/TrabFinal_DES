
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InicioScreen(
    onJogadorClick: () -> Unit,
    onAdminClick: () -> Unit
) {
    val azulFundo = Color(0xFF102A43)
    val azulCard = Color(0xFF1B3A57)
    val azulVivo = Color(0xFF3978F6)
    val verde = Color(0xFF20C997)
    val branco = Color.White
    val cinza = Color(0xFFB8C7D9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(azulFundo)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Destaque principal do jogo
        Surface(
            modifier = Modifier.size(110.dp),
            shape = RoundedCornerShape(28.dp),
            color = azulCard
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "?",
                    color = Color(0xFFFFC857),
                    fontSize = 72.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "ACERTE O\nNÚMERO",
            color = branco,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            lineHeight = 38.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Teste sua lógica e descubra o número secreto!",
            color = cinza,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Botão do jogador
        Button(
            onClick = onJogadorClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = azulVivo,
                contentColor = branco
            )
        ) {
            Text(
                text = "JOGAR",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão do administrador
        OutlinedButton(
            onClick = onAdminClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(16.dp),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = androidx.compose.ui.graphics.SolidColor(verde)
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = verde
            )
        ) {
            Text(
                text = "ADMINISTRADOR",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
