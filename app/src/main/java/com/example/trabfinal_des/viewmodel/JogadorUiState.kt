package com.example.trabfinal_des.viewmodel

import com.example.trabfinal_des.data.local.JogadorEntity

data class JogadorUiState(

    val jogadores: List<JogadorEntity> = emptyList(),

    val carregando: Boolean = false,

    val mensagem: String? = null,

    val erro: String? = null
)