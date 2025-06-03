package br.senai.sp.jandira.gestaodereceitas.model

data class Receita(
    val nome_receita: String = "",
    val ingredientes : String = "",
    val modo_preparo : String = "",
    val categoria: String = "",
    val nivel_dificudade: String = "",
    val tempo_preparo: String = "",

)
