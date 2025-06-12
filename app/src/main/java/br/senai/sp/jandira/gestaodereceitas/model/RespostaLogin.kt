package br.senai.sp.jandira.gestaodereceitas.model

data class RespostaLogin(
    val status: Boolean,
    val status_code: Int,
    val usuario: List<Cadastro>
)
