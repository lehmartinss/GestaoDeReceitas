package br.senai.sp.jandira.gestaodereceitas.service

import br.senai.sp.jandira.gestaodereceitas.model.*
import retrofit2.Call
import retrofit2.http.*

interface CadastroService {

        @Headers("Content-Type: application/json")
        @POST("usuario")
        fun insert(@Body cadastro: Cadastro): Call<RespostaCadastro>

        @Headers("Content-Type: application/json")
        @POST("login")
        fun login(@Body login: Login): Call<LoginApiResponse>

        @Headers("Content-Type: application/json")
        @PUT("usuario")
        fun update(@Body recuperarSenha: RecuperarSenha): Call<RecuperarSenha>

        @Headers("Content-Type: application/json")
        @POST("receita")
        fun publicar(@Body receita: Receita): Call<RespostaReceita>

        @Headers("Content-Type: application/json")
        @GET("usuario/{id}/receitas")
        fun listarReceitasDoUsuario(@Path("id") id: Int): Call<RespostaHome>






}
