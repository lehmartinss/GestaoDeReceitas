package br.senai.sp.jandira.gestaodereceitas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.gestaodereceitas.R
import br.senai.sp.jandira.gestaodereceitas.model.Cadastro
import br.senai.sp.jandira.gestaodereceitas.model.RecuperarSenha
import br.senai.sp.jandira.gestaodereceitas.service.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response


@Composable
    fun TelaRecuperacaoSenha(navController: NavController?){

    val email = remember { mutableStateOf("") }
    val palavra_chave = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F0D6)) // Cor de fundo da tela

        ){
            Column(
                modifier = Modifier
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = ""
                )
                Text(
                    text = stringResource(R.string.recuperacao_senha),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 12.dp),
                    color = Color(0xFF325862)

                )

                Column (
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.digite_email),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 100.dp)
                    )
                    OutlinedTextField(
                        value = email.value ,
                        onValueChange = { it ->
                            email.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon =   {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "",
                                tint = Color(0xFFECE1C4)
                            )
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
                        )
                    )
                    Text(
                        text = stringResource(R.string.palavra_chave),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 16.dp)
                    )
                    OutlinedTextField(
                        value = palavra_chave.value ,
                        onValueChange = { it ->
                            palavra_chave.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon =   {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "",
                                tint = Color(0xFFECE1C4)
                            )
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
                    Text(
                        text = stringResource(R.string.nova_senha),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 16.dp)
                    )
                    OutlinedTextField(
                        value = senha.value ,
                        onValueChange = { it ->
                            senha.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon =   {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "",
                                tint = Color(0xFFECE1C4)
                            )
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
                    //colum dos botoes
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Button(
                            onClick = {
                                val recuperarSenha = RecuperarSenha(
                                    email = email.value,
                                    palavra_chave = palavra_chave.value,
                                    senha = senha.value
                                )

                                // Fazer uma chamada para API
                                val call = RetrofitFactory()
                                    .getCadastroService()
                                    .update(recuperarSenha)

                                    call.enqueue(object : Callback<RecuperarSenha> {
                                        override fun onResponse(
                                            p0: retrofit2.Call<RecuperarSenha>, response: Response<RecuperarSenha>
                                        ) {
                                            if (response.isSuccessful) {
                                                scope.launch {
                                                    snackbarHostState.showSnackbar("Senha alterada com sucesso")
                                                }
                                                android.util.Log.i(
                                                    "API",
                                                    "Senha alterada com sucesso: ${response.body()}"
                                                )

                                                navController?.navigate("login")

                                            } else {
                                                scope.launch {
                                                    snackbarHostState.showSnackbar("Erro ao alterar senha: ${response.code()}")
                                                }
                                                android.util.Log.e(
                                                    "API",
                                                    "Erro ao alterar senha: ${response.code()}"
                                                )
                                            }
                                        }
                                        override fun onFailure(
                                            call: retrofit2.Call<RecuperarSenha>,
                                            t: Throwable
                                        ) {
                                            android.util.Log.e(
                                                "API",
                                                "Falha na requisição: ${t.message}")
                                        }
                                    })

                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF325862)
                            ),
                            modifier = Modifier
                                .padding(top = 70.dp, bottom = 4.dp)
                                .width(130.dp)
                        ) {
                            Text(
                                text = stringResource((R.string.login)),
                                color = Color.White
                            )
                        }

                    }
                }
            }
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.Center)
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
private fun TelaRecuperacaoSenhaPreview() {
    TelaRecuperacaoSenha(null)
}