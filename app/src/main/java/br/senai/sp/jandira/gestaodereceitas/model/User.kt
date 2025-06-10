package br.senai.sp.jandira.gestaodereceitas.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("nome_usuario") val nomeUsuario: String?,
    @SerializedName("email") val email: String?,
)

data class LoginApiResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("status_code") val statusCode: Int,
    @SerializedName("usuario") val userList: List<User>
)