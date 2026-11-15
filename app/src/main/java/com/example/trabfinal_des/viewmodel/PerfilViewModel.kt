
package com.example.trabfinal_des.viewmodel

import androidx.lifecycle.ViewModel
import com.example.trabfinal_des.data.local.JogadorEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PerfilViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUiState())

    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()

    fun selecionarPerfil(perfil: TipoPerfil) {
        _uiState.update {
            it.copy(
                perfilSelecionado = perfil,
                jogadorSelecionado = null
            )
        }
    }

    fun selecionarJogador(jogador: JogadorEntity) {
        _uiState.update {
            it.copy(jogadorSelecionado = jogador)
        }
    }

    fun limparPerfil() {
        _uiState.update {
            it.copy(
                perfilSelecionado = null,
                jogadorSelecionado = null
            )
        }
    }
}
