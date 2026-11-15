package com.example.trabfinal_des.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DesafioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(desafio: DesafioEntity)

    @Query("SELECT * FROM desafios ORDER BY titulo ASC")
    fun listarTodos(): Flow<List<DesafioEntity>>

    @Query("SELECT * FROM desafios WHERE ativo = 1 ORDER BY titulo ASC")
    fun listarAtivos(): Flow<List<DesafioEntity>>

    @Update
    suspend fun atualizar(desafio: DesafioEntity)

    @Delete
    suspend fun excluir(desafio: DesafioEntity)

    @Query("DELETE FROM desafios WHERE id = :id")
    suspend fun excluirPorId(id: String)
}