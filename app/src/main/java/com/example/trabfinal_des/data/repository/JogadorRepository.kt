package com.example.trabfinal_des.data.repository

import com.example.trabfinal_des.data.local.JogadorDao
import com.example.trabfinal_des.data.local.JogadorEntity
import kotlinx.coroutines.flow.Flow

class JogadorRepository(
    private val jogadorDao: JogadorDao
) {

    val jogadores: Flow<List<JogadorEntity>> =
        jogadorDao.listarTodos()

    val jogadoresAtivos: Flow<List<JogadorEntity>> =
        jogadorDao.listarAtivos()

    suspend fun inserir(jogador: JogadorEntity): Result<Unit> {
        return try {
            jogadorDao.inserir(jogador)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun atualizar(jogador: JogadorEntity): Result<Unit> {
        return try {
            jogadorDao.atualizar(jogador)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun excluir(id: String): Result<Unit> {
        return try {
            jogadorDao.excluirPorId(id)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}