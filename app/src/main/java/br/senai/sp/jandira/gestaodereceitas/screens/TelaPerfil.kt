package br.senai.sp.jandira.gestaodereceitas.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.gestaodereceitas.R
import br.senai.sp.jandira.gestaodereceitas.model.RespostaCadastro
import br.senai.sp.jandira.gestaodereceitas.model.RespostaPerfil
import br.senai.sp.jandira.gestaodereceitas.service.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun TelaPerfil(navController: NavController?) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var receitasList by remember { mutableStateOf(listOf<RespostaPerfil>()) }


    var receitaList by remember {
        mutableStateOf(listOf<RespostaPerfil>())
    }


    LaunchedEffect(Unit) {
        RetrofitFactory().getCadastroService().listarReceitasDoUsuarioPerfl()
            .enqueue(object : Callback<RespostaPerfil> {
                override fun onResponse(
                    call: Call<RespostaPerfil>,
                    response: Response<RespostaPerfil>
                ) {
                    receitasList = (response.body()?.receitasPublicadasPerfil ?: emptyList()) as List<RespostaPerfil>
                }

                override fun onFailure(call: Call<RespostaPerfil>, t: Throwable) {
                    Log.e("API_EVENTOS", "Erro: ${t.message}")
                }
            })

    }

//    // Fazer uma chamada para API
//    val call = RetrofitFactory()
//        .getCadastroService()
//        .listarReceitasDoUsuarioPerfl()
//
//    call.enqueue(object : Callback<RespostaPerfil> {
//        override fun onResponse(
//            p0: Call<RespostaPerfil>, response: Response<RespostaPerfil>
//        ) {
//            if (response.isSuccessful) {
//                scope.launch {
//                    snackbarHostState.showSnackbar("Usuário cadastrado com sucesso")
//                }
//                Log.i(
//                    "API",
//                    "Usuário cadastrado com sucesso: ${response.body()}"
//                )
//            } else {
//                scope.launch {
//                    snackbarHostState.showSnackbar("Erro ao cadastrar: ${response.code()}")
//                }
//                Log.e(
//                    "API",
//                    "Erro ao cadastrar: ${response.code()}"
//                )
//            }
//        }
//        override fun onFailure(
//            call: Call<RespostaPerfil>,
//            t: Throwable
//        ) {
//            Log.e("API", "Falha na requisição: ${t.message}")
//        }
//    })


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F0D6))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Logo e textos do usuário
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = stringResource(R.string.avatar_description),
                    modifier = Modifier.size(75.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = stringResource(R.string.nomeUser),
                        color = Color.Black,
                        fontSize = 25.sp,
                        maxLines = 1
                    )
                    Text(
                        text = stringResource(R.string.nomeDeUser),
                        color = Color.Black,
                        fontSize = 20.sp,
                        maxLines = 1
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = {
                        navController?.navigate("receita")
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF982829)),
                    modifier = Modifier
                        .height(50.dp)
                        .width(250.dp)
                        .align(Alignment.Center)
                ) {
                    Text(
                        text = stringResource(R.string.botaoPublicar),
                        color = Color.White,
                        fontSize = 16.sp,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaPerfilPreviw() {
    TelaPerfil(null)
}