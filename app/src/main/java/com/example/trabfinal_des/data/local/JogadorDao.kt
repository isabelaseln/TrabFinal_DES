package com.example.trabfinal_des.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface JogadorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(jogador: JogadorEntity)

    @Query("SELECT * FROM jogadores ORDER BY nome ASC")
    fun listarTodos(): Flow<List<JogadorEntity>>

    @Query("SELECT * FROM jogadores WHERE ativo = 1 ORDER BY nome ASC")
    fun listarAtivos(): Flow<List<JogadorEntity>>

    @Update
    suspend fun atualizar(jogador: JogadorEntity)

    @Delete
    suspend fun excluir(jogador: JogadorEntity)

    @Query("DELETE FROM jogadores WHERE id = :id")
    suspend fun excluirPorId(id: String)
}