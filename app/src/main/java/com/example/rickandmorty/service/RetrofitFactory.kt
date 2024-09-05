package com.example.rickandmorty.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    // Variavel privada para guardar a url base
    private val BASE_URL = "https://rickandmortyapi.com/api/"


    // Cliente retrofit - ele que faz as requisicoes
    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    // Vai gerar o objeto que tem as requisicoes
    fun getCharacterService(): CharacterService{
        return retrofitFactory.create(CharacterService::class.java)
    }
}