package com.example.rickandmorty.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

// Criando Arquivo (File) para Separar as Telas

// Tela refrente aos detlhes do personagem


@Composable
fun  CharacterDetails(modifier: Modifier = Modifier){

    // Criando variaveis - imoporta duas vezes
    var id by remember {
        mutableStateOf("")
    }

    // Vraiavel de estado do tipo caracter
    var character by remember {
        mutableStateOf(Character())
    }



    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Red
    ) {
        Column (
            modifier = Modifier
                .padding(24.dp)
        ) {
            OutlinedTextField(
                value = id,
                // it é o valor que estamos digitando no outlined
                onValueChange = {id = it},
                modifier = Modifier
                    .fillMaxWidth(),
                // Jogar o icon para o lado direito
                trailingIcon = {
                    // Icon button é um icone clicavel
                    IconButton(
                        onClick = {

                            // Chamada para o caracter
                            val callCharacter = RetrofitFactory()
                                .getCharacterService()
                                // toInt - A string ID vai ser convertida em INT
                                .getCharacterById(id.toInt())

                            // enqueue - enfileirar a chamada
                            // Object - pode ser qualquer coisa, estamos dizendo que ele vai herdar :
                            callCharacter.enqueue(object : retrofit2.Callback<Character>{
                                override fun onResponse(p0: Call<Character>, response: Response<Character>) {
                                    if (response.code() == 404) {
                                        character = Character()
                                    } else {
                                        character = response.body()!!

                                    }
                                }
                                override fun onFailure(p0: Call<Character>, p1: Throwable) {

                                }

                            })


                        }
                    ) {
                        // Dentro de um iconButton um icone
                        Icon(
                            // Icone de lupa
                            imageVector = Icons.Default.Search ,
                            contentDescription = ""
                        )
                    }
                },
                // Tipo de teclado que vai ser utilizado, nesse caso numero
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            Card (
                modifier = Modifier.size(120.dp),
                shape = CircleShape // Arredondar a imagem
            ){
                // Dentro do Card colocar o ASYNCIMAGE
                AsyncImage(
                    // Url da imagem
                    model =  character.image,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize() // A imagem fica do tamanho do pai
                )

            }
            // Mostra nome do personagem
            Text(text = character.name)
        }


    }
}

@Preview(showSystemUi = true)
@Composable
private  fun Preview(){
    CharacterDetails()
}