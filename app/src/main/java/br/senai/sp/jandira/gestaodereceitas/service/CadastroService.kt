package br.senai.sp.jandira.gestaodereceitas.service

import br.senai.sp.jandira.gestaodereceitas.model.Cadastro
import br.senai.sp.jandira.gestaodereceitas.model.Login
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CadastroService {

        @Headers("Content-Type: application/json")
        @POST("usuario")
        fun insert(@Body cadastro: Cadastro): Call<Cadastro>

        @POST("login")
        fun insert(@Body login: Login): Call<Login>
}
