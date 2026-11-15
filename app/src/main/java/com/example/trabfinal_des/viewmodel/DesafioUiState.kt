
package com.example.trabfinal_des.viewmodel

import com.example.trabfinal_des.data.local.DesafioEntity

data class DesafioUiState(
    val desafios: List<DesafioEntity> = emptyList(),
    val carregando: Boolean = false,
    val mensagemErro: String? = null,
    val mensagemSucesso: String? = null
)
