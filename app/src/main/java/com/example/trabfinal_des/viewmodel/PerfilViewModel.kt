
package com.example.trabfinal_des.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PerfilViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUiState())

    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()

    fun selecionarPerfil(perfil: TipoPerfil) {
        _uiState.update {
            it.copy(perfilSelecionado = perfil)
        }
    }

    fun limparPerfil() {
        _uiState.update {
            it.copy(perfilSelecionado = null)
        }
    }
}
