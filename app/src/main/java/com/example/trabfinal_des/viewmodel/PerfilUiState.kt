package com.example.trabfinal_des.viewmodel

import com.example.trabfinal_des.data.local.JogadorEntity

data class PerfilUiState(
    val perfilSelecionado: TipoPerfil? = null,
    val jogadorSelecionado: JogadorEntity? = null
)
