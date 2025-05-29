package br.senai.sp.jandira.gestaodereceitas.screens

import android.content.Context
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.gestaodereceitas.R
import br.senai.sp.jandira.gestaodereceitas.model.Cadastro
import br.senai.sp.jandira.gestaodereceitas.model.Login
import br.senai.sp.jandira.gestaodereceitas.service.RetrofitFactory
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun TelaLogin(navController: NavController?){

    val email = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F0D6)) // Cor de fundo da tela
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.logo_description),
            modifier = Modifier
                .size(250.dp)

        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.digite_email),
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 15.dp)
        )
        OutlinedTextField(
            value = email.value ,
            onValueChange = { it ->
                email.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            shape = RoundedCornerShape(12.dp),
            leadingIcon =   {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    tint = Color(0xFFECE1C4))
            } ,
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
            ),
        )
        Text(
            text = stringResource(R.string.senha),
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 10.dp)
        )
        OutlinedTextField(
            value = senha.value ,
            onValueChange = { it ->
                senha.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            shape = RoundedCornerShape(12.dp),
            leadingIcon =   {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "",
                    tint = Color(0xFFECE1C4))
            } ,
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
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = {
                    navController?.navigate("RecuperarSenha")
                },
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    text = stringResource(R.string.esqueceu_senha),
                    color = Color(0xFF982829),
                    fontSize = 15.sp
                )
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {

                val login = Login(
                    email = email.value,
                    senha = senha.value
                )

                val call = RetrofitFactory()
                    .getCadastroService()
                    .inserir(login)

                call.enqueue(object : Callback<Login> {
                    override fun onResponse(call: Call<Login>, response: Response<Login>) {
                        if (response.isSuccessful) {

                            android.util.Log.i("API", "Login realizado com sucesso!!: ${response.body()}")

                            navController?.navigate("home")

                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar("Erro ao acessar login: ${response.code()}")
                            }
                            android.util.Log.e("API", "Erro ao acessar login: ${response.code()}")
                        }
                    }
                    override fun onFailure(call: Call<Login>, t: Throwable) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Erro de conexão: ${t.message}")
                        }
                        android.util.Log.e("API", "Falha na requisição: ${t.message}")
                    }
                })
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF325862)
            ),
            modifier = Modifier
                .width(130.dp)

        ) {
            Text(
                text = stringResource((R.string.entrar)),
                color = Color.White
            )
        }
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = stringResource((R.string.nao_conta)),
            color = Color(0xFF982829),
            fontSize = 17.sp
        )
        TextButton(onClick = {
            navController?.navigate("home")
        }) {
            Text(
                text = stringResource(R.string.fazer_cadastro),
                color = Color(0xFF982829),
                fontSize = 14.sp
            )
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.Center as Alignment.Horizontal)
                .padding(16.dp),
            snackbar = { data ->
                Snackbar(
                    containerColor = Color(0xFFFFC56C),
                    contentColor = Color.Black,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .padding(10.dp)
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
}


@Preview(showSystemUi = true)
@Composable
private fun TelaLoginPreview() {
    TelaLogin(null)
}
