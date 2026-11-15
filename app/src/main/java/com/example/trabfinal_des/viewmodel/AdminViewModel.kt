package com.example.trabfinal_des.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabfinal_des.data.local.DesafioEntity
import com.example.trabfinal_des.data.repository.DesafioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class AdminViewModel(
    private val repository: DesafioRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdminUiState())
    val uiState: StateFlow<AdminUiState> = _uiState.asStateFlow()

    init {
        observarDesafios()
    }

    private fun observarDesafios() {
        viewModelScope.launch {

            repository.desafios.collect { desafios ->

                _uiState.update {
                    it.copy(
                        desafios = desafios.sortedBy { desafio ->
                            desafio.titulo
                        }
                    )
                }
            }
        }
    }

    fun sincronizarDesafios() {
        executarOperacao("Desafios sincronizados com sucesso!") {
            repository.listarRemotos()
        }
    }

    fun cadastrarDesafio(
        titulo: String,
        numeroMinimo: Int,
        numeroMaximo: Int,
        numeroSecreto: Int,
        dificuldade: String
    ) {

        if (titulo.isBlank()) {
            mostrarErro("Informe o título do desafio.")
            return
        }

        if (numeroMinimo >= numeroMaximo) {
            mostrarErro("O limite mínimo deve ser menor que o máximo.")
            return
        }

        if (numeroSecreto <= numeroMinimo ||
            numeroSecreto >= numeroMaximo
        ) {
            mostrarErro("O número secreto precisa estar dentro do intervalo.")
            return
        }

        val desafio = DesafioEntity(
            id = UUID.randomUUID().toString(),
            titulo = titulo.trim(),
            numeroMinimo = numeroMinimo,
            numeroMaximo = numeroMaximo,
            numeroSecreto = numeroSecreto,
            dificuldade = dificuldade,
            ativo = true
        )

        executarOperacao("Desafio cadastrado com sucesso!") {
            repository.inserir(desafio)
        }
    }

    fun atualizarDesafio(desafio: DesafioEntity) {

        if (desafio.titulo.isBlank()) {
            mostrarErro("Informe o título do desafio.")
            return
        }

        if (desafio.numeroMinimo >= desafio.numeroMaximo ||
            desafio.numeroSecreto <= desafio.numeroMinimo ||
            desafio.numeroSecreto >= desafio.numeroMaximo
        ) {
            mostrarErro("Confira os limites e o número secreto.")
            return
        }

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