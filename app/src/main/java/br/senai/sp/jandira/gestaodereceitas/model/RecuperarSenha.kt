package br.senai.sp.jandira.gestaodereceitas.model

data class RecuperarSenha(
    val email: String = "",
    val palavra_chave: String = "",
    val senha: String = ""
)
