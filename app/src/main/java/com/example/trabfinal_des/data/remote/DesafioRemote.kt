
package com.example.trabfinal_des.data.remote

import com.example.trabfinal_des.data.local.DesafioEntity
import kotlinx.coroutines.tasks.await

class DesafioRemote {

    private val colecao = FirebaseService.obterColecao("desafios")

    suspend fun listarDesafios(): List<DesafioEntity> {
        return try {
            val resultado = colecao.get().await()

            resultado.documents.mapNotNull { documento ->
                documento.toObject(DesafioEntity::class.java)?.copy(
                    id = documento.id
                )
            }

        } catch (e: Exception) {
            throw Exception("Erro ao listar desafios: ${e.message}")
        }
    }

    suspend fun cadastrarDesafio(desafio: DesafioEntity): String {
        return try {
            val documento = colecao.document(desafio.id)
            documento.set(desafio).await()
            documento.id

        } catch (e: Exception) {
            throw Exception("Erro ao cadastrar desafio: ${e.message}")
        }
    }

    suspend fun atualizarDesafio(desafio: DesafioEntity) {
        try {
            colecao.document(desafio.id).set(desafio).await()

        } catch (e: Exception) {
            throw Exception("Erro ao atualizar desafio: ${e.message}")
        }
    }

    suspend fun excluirDesafio(id: String) {
        try {
            colecao.document(id).delete().await()

        } catch (e: Exception) {
            throw Exception("Erro ao excluir desafio: ${e.message}")
        }
    }
}
