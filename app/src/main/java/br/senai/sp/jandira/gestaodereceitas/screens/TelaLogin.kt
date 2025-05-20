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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.gestaodereceitas.R


@Composable
fun TelaLogin(navController: NavController?){

    var nomeState = remember {
        mutableStateOf(value = "")
    }

    var isErrorState = remember {
        mutableStateOf(false)
    }

    var errorMessageState = remember {
        mutableStateOf("")
    }

    var context = LocalContext.current

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
            text = stringResource(R.string.usuario_email),
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = nomeState.value,
            onValueChange = {it ->
                nomeState.value = it
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
            ),
            isError =  isErrorState.value,
            supportingText = {
                Text(
                    text = errorMessageState.value,
                    color = Color.Red
                )
            }
        )
        Text(
            text = stringResource(R.string.senha),
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
            Text(
                modifier = Modifier
                    .padding(15.dp),
                text = stringResource(R.string.esqueceu_senha),
                color = Color(0xFF982829),
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (nomeState.value.length < 3){
                isErrorState.value = true
                errorMessageState.value = context.getString(R.string.support_nameUser)
            }else {
                // Criar o acesso a uma arquivo SharedPreferences
                val sharedNome = context
                    .getSharedPreferences("usuario", Context.MODE_PRIVATE)

                // Criar uma vareavel para editar o arquivo que acabamos de criar ou abrir
                val editor = sharedNome.edit()
                editor.putString("user_name", nomeState.value.trim())
                editor.apply()

                navController?.navigate("home")
            }
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF325862)
            )
        ) {
            Text(
                text = stringResource((R.string.entrar)),
                color = Color.White
            )
        }
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = stringResource((R.string.nao_conta)),
            color = Color(0xFF982829),
            fontSize = 16.sp
        )
        Text(
            text = stringResource((R.string.fazer_cadastro)),
            color = Color(0xFF982829),
            fontSize = 13.sp,
        )
    }
}


@Preview(showSystemUi = true)
@Composable
private fun TelaLoginPreview() {
    TelaLogin(null)
}
