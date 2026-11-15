package com.example.trabfinal_des.viewmodel

import com.example.trabfinal_des.data.local.DesafioEntity

data class AdminUiState(

    val desafios: List<DesafioEntity> = emptyList(),

    val carregando: Boolean = false,

    val mensagem: String? = null,

    val erro: String? = null
)