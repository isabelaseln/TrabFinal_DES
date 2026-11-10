package com.example.trabfinal_des.logic

enum class ResultadoPalpite {
    MAIOR,
    MENOR,
    ACERTOU,
    FORA_DO_INTERVALO
}

class GameLogic {

    fun validarPalpite(
        palpite: Int,
        numeroMinimo: Int,
        numeroMaximo: Int
    ): Boolean {

        return palpite > numeroMinimo &&
                palpite < numeroMaximo
    }

    fun avaliarPalpite(
        palpite: Int,
        numeroSecreto: Int,
        numeroMinimo: Int,
        numeroMaximo: Int
    ): ResultadoPalpite {

        if (!validarPalpite(
                palpite,
                numeroMinimo,
                numeroMaximo
            )
        ) {
            return ResultadoPalpite.FORA_DO_INTERVALO
        }

        return when {
            palpite < numeroSecreto ->
                ResultadoPalpite.MAIOR

            palpite > numeroSecreto ->
                ResultadoPalpite.MENOR

            else ->
                ResultadoPalpite.ACERTOU
        }
    }
}