package com.example.trabfinal_des.data.remote

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseService {

    val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun obterColecao(nome: String) = firestore.collection(nome)
}
