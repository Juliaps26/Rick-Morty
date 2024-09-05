package com.example.rickandmorty.service

import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Result
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// Service no nome é interface
interface CharacterService {

    // Criar uma funcao para fazer a requisicao

    // Funcao para buscar personagem pelo ID
    @GET("character/{id}") // Templete {} chamado id, pois eu nao sei o id que vai ser solicitado
                          // Id tem que ser colocado no Path no templeate chamado id
    fun getCharacterById(@Path("id") id: Int) : Call<Character>  // Devolver dentro desse call um personagem

    // Funcao para listar todos os personagens
    @GET("character")
    fun getAllCharacters(): Call<Result>  // Esse call vai me devolver uma lista de personagens




    // ANOTACOES
    // Requisicao POST - Para gravar no Banco
    // Verbo http (post, get, put)
    @POST("character")
    // Dando um nome para funcao         // Ele vai retornar :     No post o mais ideal é usar o status 201
    fun saveCharacter(@Body character: Character): Call<Result> // Call: Uma resposta do que ele criou
               // @Body anotacao de conversao



    // Requisicao PUT
    @PUT
    fun atualizaCharacter(@Body character: Character): Call<Character>
}