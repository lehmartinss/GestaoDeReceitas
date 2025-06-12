package br.senai.sp.jandira.gestaodereceitas.model

import com.google.gson.annotations.SerializedName

data class Receita(
    val id: Int?,
    val titulo: String,
    val tempo_preparo: String,
    val foto_receita: String,
    val ingrediente: String,
    val modo_preparo: String,
    val dificuldade: String,
    val id_usuario: Int,
    val classificacao_ids: List<Int>,
    val classificacao: String?,
    val usuario: Usuario?,
    val classificacoes: List<ClassificacaoReceita>?
)


data class Usuario(
    val id: Int,
    val nome_usuario: String,
    val email: String,
    val senha: String,
    val palavra_chave: String,
    val foto_perfil: String?,
    val data_criacao: String,
    val data_atualizacao: String
)

data class ReceitaPost(
    val id: Int? = null,
    val titulo: String,
    val tempo_preparo: String,
    val foto_receita: String,
    val ingrediente: String,
    val modo_preparo: String,
    val dificuldade: String,
    val id_usuario: Int,
    val classificacao_ids: List<Int> = emptyList()
)


data class ClassificacaoReceita(
    val id_classificacao: Int,
    val nome: String
)
