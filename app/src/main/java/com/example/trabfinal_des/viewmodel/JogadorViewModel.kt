package com.example.trabfinal_des.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabfinal_des.data.local.JogadorEntity
import com.example.trabfinal_des.data.repository.JogadorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class JogadorViewModel(
    private val repository: JogadorRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(JogadorUiState())
    val uiState: StateFlow<JogadorUiState> = _uiState.asStateFlow()

    init {
        observarJogadores()
    }

    private fun observarJogadores() {
        viewModelScope.launch {

            repository.jogadores.collect { jogadores ->

                _uiState.update {
                    it.copy(
                        jogadores = jogadores
                            .filter { jogador -> jogador.ativo }
                            .sortedBy { jogador -> jogador.nome }
                    )
                }
            }
        }
    }

    fun cadastrarJogador(
        nome: String,
        apelido: String
    ) {

        if (nome.isBlank() || apelido.isBlank()) {
            mostrarErro("Preencha o nome e o apelido.")
            return
        }

        val jogador = JogadorEntity(
            id = UUID.randomUUID().toString(),
            nome = nome.trim(),
            apelido = apelido.trim(),
            ativo = true
        )

        executarOperacao("Jogador cadastrado com sucesso!") {
            repository.inserir(jogador)
        }
    }

    fun atualizarJogador(jogador: JogadorEntity) {

        if (jogador.nome.isBlank() || jogador.apelido.isBlank()) {
            mostrarErro("Preencha o nome e o apelido.")
            return
        }

        executarOperacao("Jogador atualizado com sucesso!") {
            repository.atualizar(jogador)
        }
    }

    fun excluirJogador(id: String) {

        executarOperacao("Jogador excluído com sucesso!") {
            repository.excluir(id)
        }
    }

    private fun executarOperacao(
        sucesso: String,
        operacao: suspend () -> Result<Unit>
    ) {
        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    carregando = true,
                    mensagem = null,
                    erro = null
                )
            }

            operacao().fold(

                onSuccess = {
                    _uiState.update {
                        it.copy(
                            carregando = false,
                            mensagem = sucesso
                        )
                    }
                },

                onFailure = { erro ->
                    _uiState.update {
                        it.copy(
                            carregando = false,
                            erro = erro.message ?: "Ocorreu um erro."
                        )
                    }
                }
            )
        }
    }

    private fun mostrarErro(mensagem: String) {
        _uiState.update {
            it.copy(erro = mensagem, mensagem = null)
        }
    }
}