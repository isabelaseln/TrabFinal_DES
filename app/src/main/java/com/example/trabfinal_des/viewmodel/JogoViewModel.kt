package com.example.trabfinal_des.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabfinal_des.data.local.DesafioEntity
import com.example.trabfinal_des.data.repository.DesafioRepository
import com.example.trabfinal_des.logic.GameLogic
import com.example.trabfinal_des.logic.ResultadoJogo
import com.example.trabfinal_des.logic.ResultadoPalpite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JogoViewModel(
    private val repository: DesafioRepository
) : ViewModel() {

    private val gameLogic = GameLogic()

    private val _uiState = MutableStateFlow(JogoUiState())
    val uiState: StateFlow<JogoUiState> = _uiState.asStateFlow()

    init {
        observarDesafios()
    }

    private fun observarDesafios() {
        viewModelScope.launch {

            repository.desafios
                .map { lista ->
                    lista.filter { it.ativo }
                        .sortedBy { it.titulo }
                }
                .collect { desafios ->

                    _uiState.update {
                        it.copy(desafios = desafios)
                    }
                }
        }
    }

    fun iniciarPartida(desafio: DesafioEntity) {

        _uiState.update {
            it.copy(
                desafioSelecionado = desafio,
                palpite = "",
                tentativas = 0,
                mensagem = "Tente descobrir o número secreto!",
                resultado = null,
                partidaFinalizada = false,
                erro = null
            )
        }
    }

    fun atualizarPalpite(valor: String) {

        _uiState.update {
            it.copy(palpite = valor, erro = null)
        }
    }

    fun enviarPalpite() {

        val estado = _uiState.value
        val desafio = estado.desafioSelecionado

        if (desafio == null) {
            _uiState.update {
                it.copy(erro = "Nenhum desafio selecionado.")
            }
            return
        }

        if (estado.partidaFinalizada) return

        val palpite = estado.palpite.toIntOrNull()

        if (palpite == null) {
            _uiState.update {
                it.copy(erro = "Digite um número inteiro válido.")
            }
            return
        }

        val resposta = gameLogic.avaliarPalpite(
            palpite = palpite,
            numeroSecreto = desafio.numeroSecreto,
            numeroMinimo = desafio.numeroMinimo,
            numeroMaximo = desafio.numeroMaximo
        )

        if (resposta == ResultadoPalpite.FORA_DO_INTERVALO) {
            _uiState.update {
                it.copy(
                    erro = "Digite um número dentro do intervalo permitido."
                )
            }
            return
        }

        val novasTentativas = estado.tentativas + 1

        when (resposta) {

            ResultadoPalpite.MAIOR -> {
                _uiState.update {
                    it.copy(
                        tentativas = novasTentativas,
                        mensagem = "O número secreto é MAIOR!",
                        palpite = "",
                        erro = null
                    )
                }
            }

            ResultadoPalpite.MENOR -> {
                _uiState.update {
                    it.copy(
                        tentativas = novasTentativas,
                        mensagem = "O número secreto é MENOR!",
                        palpite = "",
                        erro = null
                    )
                }
            }

            ResultadoPalpite.ACERTOU -> {

                val resultado = ResultadoJogo(
                    numeroSecreto = desafio.numeroSecreto,
                    tentativas = novasTentativas,
                    acertou = true
                )

                _uiState.update {
                    it.copy(
                        tentativas = novasTentativas,
                        mensagem = "PARABÉNS! VOCÊ ACERTOU!",
                        resultado = resultado,
                        partidaFinalizada = true,
                        palpite = "",
                        erro = null
                    )
                }
            }

            ResultadoPalpite.FORA_DO_INTERVALO -> Unit
        }
    }

    fun limparPartida() {
        _uiState.update {
            it.copy(
                desafioSelecionado = null,
                palpite = "",
                tentativas = 0,
                mensagem = "",
                resultado = null,
                partidaFinalizada = false,
                erro = null
            )
        }
    }
}