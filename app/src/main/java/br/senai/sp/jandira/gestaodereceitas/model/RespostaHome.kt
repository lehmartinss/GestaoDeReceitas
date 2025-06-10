package br.senai.sp.jandira.gestaodereceitas.model

import com.google.gson.annotations.SerializedName

data class RespostaHome(
    @SerializedName("receitas")
    val receitasPublicadas: List<Receita>?
)

