package br.senai.sp.jandira.gestaodereceitas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.gestaodereceitas.R


@Composable
fun TelaHome(navController: NavController?){

    var nome_usuario = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var senha = remember { mutableStateOf("") }
    var palavra_chave = remember { mutableStateOf("") }


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
                text = stringResource(R.string.cadastro),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier,
                color = Color(0xFF325862)

            )

            Column (
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.nome_usuario_cadastro),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = nome_usuario.value ,
                    onValueChange = { it ->
                        nome_usuario.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon =   {
                        Icon(
                            imageVector = Icons.Default.Person,
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
                Text(
                    text = stringResource(R.string.email_cadastro),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = email.value ,
                    onValueChange = { it ->
                        email.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon =   {
                        Icon(
                            imageVector = Icons.Default.Email,
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
                    )
                )
                Text(
                    text = stringResource(R.string.criar_senha_cadastro),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .height(50.dp),
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
                Text(
                    text = stringResource(R.string.confirmar_senha_cadastro),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                        .height(50.dp),
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
                Text(
                    text = stringResource(R.string.telefone_cadastro),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon =   {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "",
                            tint = Color(0xFFECE1C4))
                    } ,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
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
                    Button(onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF325862)
                        ),
                        modifier = Modifier
                            .padding(top = 14.dp, bottom = 4.dp)
                    ) {
                        Text(
                            text = stringResource((R.string.criar_conta)),
                            color = Color.White
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(4.dp),
                        text = stringResource(R.string.ja_conta),
                        color = Color(0xFF982829),
                        fontSize = 18.sp
                    )
                    TextButton(
                        onClick = {
                            navController?.navigate("login")
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.fazer_login),
                            color = Color(0xFF982829),
                            fontSize = 16.sp
                        )
                    }

                }
            }


        }



    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaHomePreview() {
    TelaHome(null)
}
