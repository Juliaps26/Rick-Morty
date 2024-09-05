package com.example.rickandmorty.model

// Usando a data class para facilitar
data class Info(
    // Criando Atributos da Classe Info
    val count: Int = 0,
    val pages: Int =0,
    val next: String = "",
    val prev: String = ""
)
