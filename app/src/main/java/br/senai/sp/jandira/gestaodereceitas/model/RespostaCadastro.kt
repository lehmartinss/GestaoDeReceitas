package br.senai.sp.jandira.gestaodereceitas.model

data class RespostaCadastro(
    val status_code: Int,
    val message: String = "",
    val usuario: Cadastro
)
