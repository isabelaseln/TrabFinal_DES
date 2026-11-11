package com.example.trabfinal_des.viewmodel

import com.example.trabfinal_des.data.local.DesafioEntity
import com.example.trabfinal_des.logic.ResultadoJogo

data class JogoUiState(

    val desafios: List<DesafioEntity> = emptyList(),

    val desafioSelecionado: DesafioEntity? = null,

    val palpite: String = "",

    val tentativas: Int = 0,

    val mensagem: String = "",

    val resultado: ResultadoJogo? = null,

    val partidaFinalizada: Boolean = false,

    val erro: String? = null
)