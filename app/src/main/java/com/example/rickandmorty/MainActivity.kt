 package com.example.rickandmorty


 // Apagando imports nao usados (control+ option + o)
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.rickandmorty.screens.CharacterDetails
import com.example.rickandmorty.screens.ListAllCharacter
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge() - Para estilizar a barra de comeco da aplicacao
        setContent {
            RickAndMortyTheme {
               // Chamando a funcao
//                CharacterDetails()
                ListAllCharacter()

                // Importei a funcao quando separei
            }
        }
    }
}


