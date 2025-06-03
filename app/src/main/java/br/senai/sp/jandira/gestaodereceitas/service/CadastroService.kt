package br.senai.sp.jandira.gestaodereceitas.service

import br.senai.sp.jandira.gestaodereceitas.model.Cadastro
import br.senai.sp.jandira.gestaodereceitas.model.Login
import br.senai.sp.jandira.gestaodereceitas.model.Receita
import br.senai.sp.jandira.gestaodereceitas.model.RecuperarSenha
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface CadastroService {

        @Headers("Content-Type: application/json")
        @POST("usuario")
        fun insert(@Body cadastro: Cadastro): Call<Cadastro>

        @Headers("Content-Type: application/json")
        @POST("login")
        fun inserir(@Body login: Login): Call<Login>

        @Headers("Content-Type: application/json")
        @PUT("usuario")
        fun update(@Body recuperarSenha: RecuperarSenha): Call<RecuperarSenha>

        @Headers("Content-Type: application/json")
        @POST("usuario")
        fun publicar(@Body receita: Receita): Call<Receita>


}
