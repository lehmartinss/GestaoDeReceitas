package br.senai.sp.jandira.gestaodereceitas.model

data class Receita(
    val id: Int,
    val id_usuario: Int,
    val titulo: String = "",
    val ingrediente : String = "",
    val modo_preparo : String = "",
    val dificuldade: String = "",
    val tempo_preparo: String = "",
    val categoria: String = ""


)
