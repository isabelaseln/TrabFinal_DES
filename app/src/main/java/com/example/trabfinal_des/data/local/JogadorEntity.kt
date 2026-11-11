package com.example.trabfinal_des.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jogadores")
data class JogadorEntity(

    @PrimaryKey
    val id: String,

    val nome: String,

    val apelido: String,

    val ativo: Boolean = true
)