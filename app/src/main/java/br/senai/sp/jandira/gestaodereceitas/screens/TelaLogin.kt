package br.senai.sp.jandira.gestaodereceitas.screens

import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import br.senai.sp.jandira.gestaodereceitas.R
import br.senai.sp.jandira.gestaodereceitas.model.Login
import br.senai.sp.jandira.gestaodereceitas.model.LoginApiResponse
import br.senai.sp.jandira.gestaodereceitas.service.RetrofitFactory
import br.senai.sp.jandira.gestaodereceitas.utils.SharedPreferencesUtils
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun TelaLogin(navController: NavController?) {

    val email = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                snackbar = { data ->
                    Snackbar(
                        containerColor = Color(0xFFFFC56C),
                        contentColor = Color.Black,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                    ) {
                        Text(
                            text = data.visuals.message,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F0D6))
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(R.string.logo_description),
                    modifier = Modifier.size(250.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = stringResource(R.string.digite_email),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 10.dp)
                )
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF325862),
                        unfocusedContainerColor = Color(0xFF325862),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLeadingIconColor = Color.White,
                        unfocusedLeadingIconColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    )
                )
                Text(
                    text = stringResource(R.string.senha),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 10.dp)
                )
                OutlinedTextField(
                    value = senha.value,
                    onValueChange = { senha.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF325862),
                        unfocusedContainerColor = Color(0xFF325862),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLeadingIconColor = Color.White,
                        unfocusedLeadingIconColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { navController?.navigate("RecuperarSenha") },
                        modifier = Modifier.padding(3.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.esqueceu_senha),
                            color = Color(0xFF982829),
                            fontSize = 15.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    onClick = {
                        val login = Login(email.value.trim(), senha.value.trim())

                        val call = RetrofitFactory()
                            .getCadastroService()
                            .login(login)

                        call.enqueue(object : Callback<LoginApiResponse> {
                            @OptIn(UnstableApi::class)
                            override fun onResponse(call: Call<LoginApiResponse>, response: Response<LoginApiResponse>) {
                                if (response.isSuccessful) {
                                    val apiResponse = response.body()
                                    android.util.Log.i("API_DEBUG", "Resposta do login (completo): $apiResponse")

                                    if (apiResponse != null && apiResponse.userList.isNotEmpty()) {
                                        val userId = apiResponse.userList[0].id

<<<<<<< HEAD
                                        if (userId != null && userId > 0) {
                                            SharedPreferencesUtils.saveUserId(context, userId)
                                            val testId = SharedPreferencesUtils.getUserId(context)
                                            Log.i("API", "ID salvo e recuperado: $testId")
                                            navController?.navigate("home")
                                        } else {
                                            scope.launch {
                                                snackbarHostState.showSnackbar("ID do usuário inválido recebido.")
                                            }
                                            android.util.Log.e("API", "ID do usuário é nulo ou inválido.")
                                        }
=======
                                        SharedPreferencesUtils.saveUserId(context, userId) // salva o id
                                        android.util.Log.i("API", "Login realizado com sucesso! ID do usuario: $userId")
                                        navController?.navigate("perfil")
>>>>>>> cc93f028bfecf0e5b44d90b8e1ff1ec1f29b1ee6
                                    } else {
                                        scope.launch {
                                            snackbarHostState.showSnackbar("Erro ao acessar login. Email ou senha incorretos.")
                                        }
                                        android.util.Log.e("API", "Lista de usuário vazia ou nula.")
                                    }
                                } else {
                                    val errorBody = response.errorBody()?.string()
                                    android.util.Log.e("API", "Erro login: ${response.code()} - $errorBody")
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Erro ao acessar login: Email ou senha incorretos.")
                                    }
                                }
                            }

                            override fun onFailure(call: Call<LoginApiResponse>, t: Throwable) {
                                android.util.Log.e("API", "Falha na requisição: ${t.message}", t)
                                scope.launch {
                                    snackbarHostState.showSnackbar("Erro de conexão: Verifique sua internet.")
                                }
                            }
                        })
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF325862)),
                    modifier = Modifier.width(130.dp)
                )
                {
                    Text(text = stringResource(R.string.entrar), color = Color.White)
                }
                Text(
                    modifier = Modifier.padding(1.dp),
                    text = stringResource(R.string.nao_conta),
                    color = Color(0xFF982829),
                    fontSize = 17.sp
                )
                TextButton(onClick = { navController?.navigate("cadastro") }) {
                    Text(
                        text = stringResource(R.string.fazer_cadastro),
                        color = Color(0xFF982829),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaLoginPreview() {
    TelaLogin(null)
}
