package br.senai.sp.jandira.gestaodereceitas.model

data class Receita(
    val id: Int,
    val titulo: String = "",
    val tempo_preparo: String = "",
    val foto_receita: String = "",
    val ingrediente: String = "",
    val modo_preparo: String = "",
    val dificuldade: String = "",
    val id_usuario: Int,
    val categoria: String = "",
     // Adicionado campo esperado pela API
)
