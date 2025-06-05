package br.senai.sp.jandira.gestaodereceitas.screens

import android.graphics.drawable.Icon
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.gestaodereceitas.R
import br.senai.sp.jandira.gestaodereceitas.model.Receita
import br.senai.sp.jandira.gestaodereceitas.model.RespostaReceita
import br.senai.sp.jandira.gestaodereceitas.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaReceita(navController: NavController?){

    val titulo = remember { mutableStateOf("") }
    val ingrediente = remember { mutableStateOf("") }
    val modo_preparo = remember { mutableStateOf("") }
    val dificuldade = remember { mutableStateOf("") }
    val tempo_preparo = remember { mutableStateOf("") }
    val categoria = remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }
    val categorias = listOf("Salgado", "Doce", "Carne", "Ave", "Peixe", "Sem Glúten", "Sem Lactose",)
    val id = remember { mutableStateOf("") }
    val id_usuario= remember { mutableStateOf("") }
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

                if (imageUri.value == null) {
                    Button(
                        onClick = {
                            launcher.launch("image/*")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF325862)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.selecionar_foto),
                            color = Color.White
                        )
                    }
                }
                imageUri.value?.let { uri ->
                    Image(
                        painter = rememberAsyncImagePainter(model = uri),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .height(95.dp)
                            .width(180.dp)
                            .clip(RoundedCornerShape(25.dp)),
                        contentScale = ContentScale.Fit
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
                    value = titulo.value ,
                    onValueChange = { it ->
                        titulo.value = it
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
                    value = ingrediente.value ,
                    onValueChange = { it ->
                        ingrediente.value = it
                    },
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 5.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
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

                ExposedDropdownMenuBox(
                    expanded = expanded.value,
                    onExpandedChange = { expanded.value = !expanded.value },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 5.dp)
                ) {
                    OutlinedTextField(
                        value = categoria.value,
                        onValueChange = { it ->
                            categoria.value = it
                        },
                        readOnly = true,
                        placeholder = {
                            Text(
                                text = stringResource(R.string.selecione),
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .height(55.dp)
                            .width(180.dp),
                        shape = RoundedCornerShape(12.dp),
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 14.sp
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF325862),
                            unfocusedContainerColor = Color(0xFF325862),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.White
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                        modifier = Modifier
                            .background(Color(0xFF982829))
                    ) {
                        categorias.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = item,
                                        color = Color.White
                                    )
                                },
                                onClick = {
                                    categoria.value = item
                                    expanded.value = false
                                },
                                modifier = Modifier.background(Color(0xFF982829))
                            )
                        }
                    }
                }
                Text(
                    text = stringResource(R.string.nivel_dificuldade),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = dificuldade.value ,
                    onValueChange = { it ->
                        dificuldade.value = it
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

                //box dos botoes
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 16.dp, bottom = 4.dp),
                    contentAlignment = Alignment.BottomEnd
                ){
                    Button(
                        onClick = {
                            val receita = Receita(

                                titulo = titulo.value,
                                ingrediente = ingrediente.value,
                                modo_preparo = modo_preparo.value,
                                dificuldade = dificuldade.value,
                                tempo_preparo = tempo_preparo.value,
                                categoria = categoria.value,
                                id = id.hashCode(),
                                id_usuario = id_usuario.hashCode()
                            )

                            // Fazer uma chamada para API
                            val call = RetrofitFactory()
                                .getCadastroService()
                                .publicar(receita)

                            call.enqueue(object : Callback<RespostaReceita> {
                                override fun onResponse(
                                    p0: Call<RespostaReceita>, response: Response<RespostaReceita>
                                ) {
                                    if (response.isSuccessful) {
                                        scope.launch {
                                            snackbarHostState.showSnackbar("Receita publicada com sucesso")
                                        }
                                        Log.i(
                                            "API",
                                            "Receita publicada com sucesso: ${response.body()}"
                                        )

//                                                navController?.navigate("login")

                                    } else {
                                        scope.launch {
                                            snackbarHostState.showSnackbar("Erro ao publicar receita: existe campos que não foram preenchidos")
                                        }
                                        Log.e(
                                            "API",
                                            "Erro ao publicar receita: ${response.code()}"
                                        )
                                    }
                                }
                                override fun onFailure(p0: Call<RespostaReceita>, t: Throwable) {
                                    Log.e(
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