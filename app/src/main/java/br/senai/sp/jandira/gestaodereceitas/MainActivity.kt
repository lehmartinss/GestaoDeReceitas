package br.senai.sp.jandira.gestaodereceitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.gestaodereceitas.screens.TelaCadastro
import br.senai.sp.jandira.gestaodereceitas.screens.TelaHome
import br.senai.sp.jandira.gestaodereceitas.screens.TelaLogin
import br.senai.sp.jandira.gestaodereceitas.screens.TelaPerfil
import br.senai.sp.jandira.gestaodereceitas.screens.TelaReceita
import br.senai.sp.jandira.gestaodereceitas.screens.TelaRecuperacaoSenha
import br.senai.sp.jandira.gestaodereceitas.ui.theme.GestaoDeReceitasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestaoDeReceitasTheme {
                var navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login") {
                        TelaLogin(navController)
                    }
                    composable("home") {
                        TelaHome(navController)
                    }

                    composable("cadastro") {
                        TelaCadastro(navController)
                    }
                    composable("RecuperarSenha") {
                        TelaRecuperacaoSenha(navController)
                    }
                    composable("receita") {
                        TelaReceita(navController)
                    }
<<<<<<< HEAD
                    composable("home") {
                        TelaHome(navController)
=======
                    composable("perfil") {
                        TelaPerfil(navController)
>>>>>>> cc93f028bfecf0e5b44d90b8e1ff1ec1f29b1ee6
                    }

                }

            }
        }
    }
}

