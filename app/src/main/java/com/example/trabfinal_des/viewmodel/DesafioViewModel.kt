
package com.example.trabfinal_des.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabfinal_des.data.local.DesafioEntity
import com.example.trabfinal_des.data.repository.DesafioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DesafioViewModel(
    private val repository: DesafioRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DesafioUiState())
    val uiState: StateFlow<DesafioUiState> = _uiState.asStateFlow()

    init {
        observarDesafios()
    }

    private fun observarDesafios() {
        viewModelScope.launch {
            repository.desafios.collect { desafios ->
                _uiState.value = _uiState.value.copy(
                    desafios = desafios
                )
            }
        }
    }

    fun listarDesafios() {
        executarOperacao("Desafios sincronizados com sucesso!") {
            repository.listarRemotos()
        }
    }

    fun cadastrarDesafio(desafio: DesafioEntity) {
        executarOperacao("Desafio cadastrado com sucesso!") {
            repository.inserir(desafio)
        }
    }

    fun atualizarDesafio(desafio: DesafioEntity) {
        executarOperacao("Desafio atualizado com sucesso!") {
            repository.atualizar(desafio)
        }
    }

    fun excluirDesafio(id: String) {
        executarOperacao("Desafio excluído com sucesso!") {
            repository.excluir(id)
        }
    }

    private fun executarOperacao(
        sucesso: String,
        operacao: suspend () -> Result<Unit>
    ) {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                carregando = true,
                mensagemErro = null,
                mensagemSucesso = null
            )

            operacao().fold(

                onSuccess = {
                    _uiState.value = _uiState.value.copy(
                        carregando = false,
                        mensagemSucesso = sucesso
                    )
                },

                onFailure = { erro ->
                    _uiState.value = _uiState.value.copy(
                        carregando = false,
                        mensagemErro = erro.message ?: "Ocorreu um erro."
                    )
                }
            )
        }
    }
}
