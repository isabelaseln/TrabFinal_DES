package com.example.trabfinal_des.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        DesafioEntity::class,
        JogadorEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun desafioDao(): DesafioDao

    abstract fun jogadorDao(): JogadorDao
}