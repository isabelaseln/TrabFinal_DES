package com.example.trabfinal_des.viewmodel

import com.example.acertenumero.data.local.DesafioEntity
import com.example.acertenumero.logic.ResultadoJogo

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