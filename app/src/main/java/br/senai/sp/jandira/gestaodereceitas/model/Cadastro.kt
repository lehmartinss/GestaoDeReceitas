package br.senai.sp.jandira.gestaodereceitas.model

data class Cadastro(
    val id: Int,
    val nome_usuario: String = "",
    val email: String = "",
    val senha: String = "",
    val palavra_chave: String = ""
)

