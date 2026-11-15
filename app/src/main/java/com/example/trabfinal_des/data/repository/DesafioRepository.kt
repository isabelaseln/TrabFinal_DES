
package com.example.trabfinal_des.data.repository

import com.example.trabfinal_des.data.local.DesafioDao
import com.example.trabfinal_des.data.local.DesafioEntity
import com.example.trabfinal_des.data.remote.DesafioRemote
import kotlinx.coroutines.flow.Flow

class DesafioRepository(
    private val desafioDao: DesafioDao,
    private val desafioRemote: DesafioRemote = DesafioRemote()
) {

    val desafios: Flow<List<DesafioEntity>> =
        desafioDao.listarTodos()

    suspend fun listarRemotos(): Result<Unit> {
        return try {
            val desafiosRemotos = desafioRemote.listarDesafios()

            desafiosRemotos.forEach { desafio ->
                desafioDao.inserir(desafio)
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun inserir(desafio: DesafioEntity): Result<Unit> {
        return try {
            desafioRemote.cadastrarDesafio(desafio)
            desafioDao.inserir(desafio)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun atualizar(desafio: DesafioEntity): Result<Unit> {
        return try {
            desafioRemote.atualizarDesafio(desafio)
            desafioDao.atualizar(desafio)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun excluir(id: String): Result<Unit> {
        return try {
            desafioRemote.excluirDesafio(id)
            desafioDao.excluirPorId(id)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
