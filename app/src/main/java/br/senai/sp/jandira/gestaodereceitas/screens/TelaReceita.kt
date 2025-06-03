package br.senai.sp.jandira.gestaodereceitas.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.gestaodereceitas.R
import br.senai.sp.jandira.gestaodereceitas.model.Receita
import br.senai.sp.jandira.gestaodereceitas.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun TelaReceita(navController: NavController?){

    val nome_receita = remember { mutableStateOf("") }
    val ingredientes = remember { mutableStateOf("") }
    val modo_preparo = remember { mutableStateOf("") }
    val categoria = remember { mutableStateOf("") }
    val nivel_dificudade = remember { mutableStateOf("") }
    val tempo_preparo = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri.value = uri
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F0D6)) // Cor de fundo da tela

    ){
        Column(
            modifier = Modifier
                .padding(32.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Row (
                modifier = Modifier
                    .padding(top = 18.dp),

                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(
                        R.string.logo_description
                    ),
                    modifier = Modifier
                        .size(75.dp)
                )

                Text(
                    text = stringResource(R.string.publicar_receita),
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(top = 20.dp, start = 18.dp),
                    color = Color.Black

                )
            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.foto),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 6.dp)
                )

                Button(
                    onClick = {
                        launcher.launch("image/*")
                    },
                    colors = ButtonDefaults.buttonColors
                        (containerColor = Color(0xFF325862)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = stringResource(R.string.selecionar_foto),
                        color = Color.White
                    )
                }

                imageUri.value?.let { uri ->
                    Image(
                        painter = rememberAsyncImagePainter(model = uri),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .height(150.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = stringResource(R.string.nome_receita),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 5.dp)
                )
                OutlinedTextField(
                    value = nome_receita.value ,
                    onValueChange = { it ->
                        nome_receita.value = it
                    },
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 5.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
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
                    text = stringResource(R.string.ingredientes),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = ingredientes.value ,
                    onValueChange = { it ->
                        ingredientes.value = it
                    },
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 5.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
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
                    text = stringResource(R.string.modo_preparo),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = modo_preparo.value ,
                    onValueChange = { it ->
                        modo_preparo.value = it
                    },
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 5.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
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
                    text = stringResource(R.string.Categoria),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = categoria.value ,
                    onValueChange = { it ->
                        categoria.value = it
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 5.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
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
                    text = stringResource(R.string.nivel_dificuldade),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = nivel_dificudade.value ,
                    onValueChange = { it ->
                        nivel_dificudade.value = it
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 5.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
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
                    text = stringResource(R.string.tempo_preparo),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = tempo_preparo.value ,
                    onValueChange = { it ->
                        tempo_preparo.value = it
                    },
                    modifier = Modifier
                        .width(275.dp)
                        .padding(top = 5.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
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
                        .width(100.dp)
                        .height(100.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End,
                ){
                    Button(
                        onClick = {
                            val receita = Receita(
                                nome_receita = nome_receita.value,
                                ingredientes = ingredientes.value,
                                modo_preparo = modo_preparo.value,
                                categoria = categoria.value,
                                nivel_dificudade = nivel_dificudade.value,
                                tempo_preparo = tempo_preparo.value,
                            )

                            // Fazer uma chamada para API
                            val call = RetrofitFactory()
                                .getCadastroService()
                                .publicar(receita)

                            call.enqueue(object : Callback<Receita> {
                                override fun onResponse(
                                    p0: retrofit2.Call<Receita>, response: Response<Receita>
                                ) {
                                    if (response.isSuccessful) {
                                        scope.launch {
                                            snackbarHostState.showSnackbar("Receita publicada com sucesso")
                                        }
                                        android.util.Log.i(
                                            "API",
                                            "Receita publicada com sucesso: ${response.body()}"
                                        )

//                                                navController?.navigate("login")

                                    } else {
                                        scope.launch {
                                            snackbarHostState.showSnackbar("Erro ao publicar receita: existe campos que não foram preenchidos")
                                        }
                                        android.util.Log.e(
                                            "API",
                                            "Erro ao publicar receita: ${response.code()}"
                                        )
                                    }
                                }
                                override fun onFailure(p0: Call<Receita>, t: Throwable) {
                                    android.util.Log.e(
                                        "API",
                                        "Falha na requisição: ${t.message}")
                                }
                            })

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF982829)
                        ),
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 4.dp)
                            .width(130.dp)
                    ) {
                        Text(
                            text = stringResource((R.string.publicar_receita_botao)),
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
private fun TelaReceitaReview() {
    TelaReceita(null)
}