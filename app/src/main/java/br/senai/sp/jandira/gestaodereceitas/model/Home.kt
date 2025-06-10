package br.senai.sp.jandira.gestaodereceitas.model

data class Home(
    val id: Int? = null,
    val id_usuario: Int = 0,
    val titulo: String = "",
    val tempo_preparo: String = "",
    val foto_receita: String = "",
    val ingrediente: String = "",
    val modo_preparo: String = "",
    val dificuldade: String = "",
    val classificacao_ids: List<Int> = emptyList(),
    val classificacao: String? = null
)
