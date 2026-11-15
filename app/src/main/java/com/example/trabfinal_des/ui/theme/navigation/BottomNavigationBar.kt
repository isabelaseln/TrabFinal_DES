
package com.example.trabfinal_des.ui.theme.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavigationBar(
    rotaAtual: String?,
    modoAdmin: Boolean,
    onNavegar: (String) -> Unit
) {

    val destinos = if (modoAdmin) {
        listOf(
            Pair("admin", "Painel"),
            Pair("admin_desafios", "Desafios"),
            Pair("admin_jogadores", "Jogadores")
        )
    } else {
        listOf(
            Pair("inicio", "Início"),
            Pair("selecionar_jogador", "Jogar"),
            Pair("desafios", "Desafios")
        )
    }

    NavigationBar(
        containerColor = Color(0xFF202D43),
        contentColor = Color.White
    ) {
        destinos.forEach { destino ->

            NavigationBarItem(
                selected = rotaAtual == destino.first,

                onClick = {
                    if (rotaAtual != destino.first) {
                        onNavegar(destino.first)
                    }
                },

                icon = {
                    Text(
                        text = destino.second.take(1),
                        color = Color.White
                    )
                },

                label = {
                    Text(destino.second)
                },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color(0xFF64B5F6),
                    indicatorColor = Color(0xFF3978F6),
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.LightGray
                )
            )
        }
    }
}
