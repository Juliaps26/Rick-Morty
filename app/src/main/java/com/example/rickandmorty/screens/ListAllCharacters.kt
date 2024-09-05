package com.example.rickandmorty.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Result
import com.example.rickandmorty.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Criando tela para listar todos os personagens


// Funcao de Composicao
@Composable
fun ListAllCharacter() {

    // Criando Variaveis de estado
// fazer os imports
    var characterList by remember {
        mutableStateOf(listOf<Character>())
    }

// Efetuar a requisicao para a API
    val callCharacaterList = RetrofitFactory()
        .getCharacterService()
        .getAllCharacters()


    // Encaminhar a Consulta
    callCharacaterList.enqueue(
        // CallBack do retrofit
        object : Callback<Result> {  // Importar as Funcoes (implement members)
            override fun onResponse(p0: Call<Result>, response: Response<Result>) {
               characterList = response.body()!!.results
            }

            override fun onFailure(p0: Call<Result>, p1: Throwable) {
            }

        }
    )


    Surface (
        modifier = Modifier
            // Tamanho da Tela
            .fillMaxSize(),
        color = Color(0xFFE91E63)
    ) {


        Column (
            modifier = Modifier
                .padding(16.dp)
        )

        {
            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Rick & Morty API",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold

            )
            Spacer(modifier = Modifier.padding(10.dp))

            // Lazy columnn para lista
            LazyColumn {
                // Funciona como um loop
                items(characterList){
                    // Vamos criar os cards separados
                    CharacterCard(it)
                }
            }
        }

    }

}

@Composable
// Criando Funcao para os cards
fun CharacterCard(character: Character) {
    Card(
        modifier = Modifier
            // Padding sempre ficar em cima, criando uma margem de 4.dp na parte de baixo
            .padding(bottom = 4.dp)
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFC8CBFD)
        )
    ) {
        Row {
            Card (
                modifier = Modifier
                    .size(100.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                )
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = ""
                )

            }
            Column (
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = character.name,
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = character.species
                    ,
                    color = Color(0xFA830008)
                )
            }
        }

    }
}

// Digitar prev já vai automatico a base
@Preview
@Composable
private fun ListAllCharactersPreview() {
    ListAllCharacter()
}