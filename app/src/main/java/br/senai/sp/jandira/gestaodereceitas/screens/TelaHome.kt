package br.senai.sp.jandira.gestaodereceitas.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.senai.sp.jandira.gestaodereceitas.R
import br.senai.sp.jandira.gestaodereceitas.model.ClassificacaoReceita
import br.senai.sp.jandira.gestaodereceitas.model.Receita
import br.senai.sp.jandira.gestaodereceitas.model.RespostaHome
import br.senai.sp.jandira.gestaodereceitas.service.RetrofitFactory
import br.senai.sp.jandira.gestaodereceitas.utils.SharedPreferencesUtils
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun TelaHome(navController: NavController?) {
    val context = LocalContext.current

    // Pega o userId como Int (modificação: confirma que userId é Int)
    val userId = SharedPreferencesUtils.getUserId(context)

    val coroutineScope = rememberCoroutineScope()
    val receitasState = remember { mutableStateOf<List<Receita>>(emptyList()) }

    val currentBackStackEntry = navController?.currentBackStackEntryAsState()
    val savedStateHandle = currentBackStackEntry?.value?.savedStateHandle

    fun carregarReceitas() {
        if (userId != null && userId > 0) {  // Verifica se userId é válido
            RetrofitFactory()
                .getCadastroService()
                .listarReceitasDoUsuario(userId)  // Passa o Int diretamente (corrigido)
                .enqueue(object : Callback<RespostaHome> {
                    override fun onResponse(call: Call<RespostaHome>, response: Response<RespostaHome>) {
                        if (response.isSuccessful) {
                            val receitas = response.body()?.receitasPublicadas.orEmpty()
                            receitasState.value = receitas
                            Log.i("API", "Receitas carregadas: ${receitas.size}")
                        } else {
                            Log.e("API", "Erro ${response.code()}: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<RespostaHome>, t: Throwable) {
                        Log.e("API", "Erro de rede: ${t.localizedMessage}")
                    }
                })
        } else {
            Log.w("API", "ID do usuário está nulo ou inválido, não foi possível buscar receitas.")
        }
    }

    LaunchedEffect(userId) {
        carregarReceitas()
    }

    LaunchedEffect(savedStateHandle) {
        savedStateHandle?.getLiveData<Boolean>("reload_receitas")?.observeForever { shouldReload ->
            if (shouldReload == true) {
                carregarReceitas()
                savedStateHandle["reload_receitas"] = false
            }
        }
    }

    val classificacoesDisponiveis = listOf(
        ClassificacaoReceita(4, "SALGADO"),
        ClassificacaoReceita(5, "DOCE"),
        ClassificacaoReceita(6, "CARNE"),
        ClassificacaoReceita(7, "AVE"),
        ClassificacaoReceita(8, "PEIXE"),
        ClassificacaoReceita(9, "SEM GLÚTEN"),
        ClassificacaoReceita(10, "SEM LACTOSE")
    )

    val listState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F0D6))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(R.string.logo_description),
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = stringResource(R.string.pesquisar)) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFF325862),
                        focusedBorderColor = Color(0xFF325862)
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                IconButton(onClick = {
                    val firstVisible = listState.firstVisibleItemIndex
                    val targetIndex = maxOf(0, firstVisible - 3)
                    coroutineScope.launch {
                        listState.animateScrollToItem(targetIndex)
                    }
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar")
                }

                LazyRow(
                    state = listState,
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(classificacoesDisponiveis) { classificacao ->
                        Button(
                            onClick = { /* ação ao clicar */ },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF982829)),
                            modifier = Modifier
                                .height(32.dp)
                                .width(100.dp)
                        ) {
                            Text(
                                text = classificacao.nome,
                                color = Color.White,
                                fontSize = 12.sp,
                                maxLines = 1
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Minhas Receitas", fontSize = 20.sp, color = Color(0xFF325862))

            Spacer(modifier = Modifier.height(12.dp))

            receitasState.value.forEach { receita ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(text = receita.titulo, fontSize = 18.sp, color = Color(0xFF982829))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Classificação: ${receita.classificacoes ?: "N/A"}",
                            fontSize = 14.sp
                        )
                        Text(text = "Ingredientes: ${receita.ingrediente}", fontSize = 14.sp)
                        Text(text = "Modo de preparo: ${receita.modo_preparo}", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TelaHomePreview() {
    TelaHome(navController = null)
}
