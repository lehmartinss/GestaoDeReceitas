package br.senai.sp.jandira.gestaodereceitas.model

import com.google.gson.annotations.SerializedName

data class RespostaPerfil(
    @SerializedName("receitas")
    val receitasPublicadasPerfil: List<Receita>?
)
