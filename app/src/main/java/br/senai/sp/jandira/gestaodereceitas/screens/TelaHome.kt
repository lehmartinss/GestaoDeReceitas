package br.senai.sp.jandira.gestaodereceitas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.gestaodereceitas.R

@Composable
fun TelaHome(navController: NavController?){


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F0D6)) // Cor de fundo da tela

    ){
        Column(
            modifier = Modifier
                .padding(30.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
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
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, start = 15.dp)
                    ,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFF325862),
                        focusedBorderColor = Color(0xFF325862)
                    ),
                    label = {
                        Text(
                            text = stringResource(R.string.pesquisar),
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = Color.Gray
                        )
                    },
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(90.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFF3347B0)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.categoria1),
                        color = Color(0xF5FAFAFA)
                    )
                }
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(90.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFFFFC23D)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.categoria2),
                        color = Color(0XFF3347B0)
                    )
                }
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(90.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFFFFC23D)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.categoria3),
                        color = Color(0XFF3347B0)
                    )
                }
            }
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        start = 20.dp,
                        end = 20.dp
                    ),
                color =
                    Color(color = 0xFFFFC23D)
            )


        }
    }


@Preview(showSystemUi = true)
@Composable
private fun TelaHomePreview() {
    TelaHome(null)
}