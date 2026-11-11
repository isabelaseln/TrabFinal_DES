package com.example.trabfinal_des.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.acertenumero.data.repository.DesafioRepository
import com.example.acertenumero.data.repository.JogadorRepository

class ViewModelFactory(
    private val desafioRepository: DesafioRepository,
    private val jogadorRepository: JogadorRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return when {

            modelClass.isAssignableFrom(JogoViewModel::class.java) ->
                JogoViewModel(desafioRepository) as T

            modelClass.isAssignableFrom(AdminViewModel::class.java) ->
                AdminViewModel(desafioRepository) as T

            modelClass.isAssignableFrom(JogadorViewModel::class.java) ->
                JogadorViewModel(jogadorRepository) as T

            else -> throw IllegalArgumentException(
                "ViewModel desconhecido: ${modelClass.name}"
            )
        }
    }
}