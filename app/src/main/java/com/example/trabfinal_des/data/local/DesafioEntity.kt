
package com.example.trabfinal_des.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "desafios")
data class DesafioEntity(

    @PrimaryKey
    val id: String = "",

    val titulo: String = "",

    val numeroMinimo: Int = 1,

    val numeroMaximo: Int = 100,

    val numeroSecreto: Int = 84,

    val dificuldade: String = "",

    val ativo: Boolean = true
)
