package com.example.rickandmorty.model

data class Character(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    // O atributo origin do tipo origin vai receber um origin vazio
    val origin: Origin = Origin(),
    val location: Location = Location(),
    val image: String = "",
    // Criar uma lista vazia sem nada dentro
    val episodes: List<String> = listOf(),
    val url: String = "",
    val created: String = "",
)
