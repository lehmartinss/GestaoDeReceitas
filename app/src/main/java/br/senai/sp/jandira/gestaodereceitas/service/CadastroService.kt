// Em br.senai.sp.jandira.gestaodereceitas.service.CadastroService
package br.senai.sp.jandira.gestaodereceitas.service

import br.senai.sp.jandira.gestaodereceitas.model.Cadastro
import br.senai.sp.jandira.gestaodereceitas.model.Login
import br.senai.sp.jandira.gestaodereceitas.model.Receita
import br.senai.sp.jandira.gestaodereceitas.model.RecuperarSenha
import br.senai.sp.jandira.gestaodereceitas.model.RespostaCadastro
import br.senai.sp.jandira.gestaodereceitas.model.RespostaReceita
import br.senai.sp.jandira.gestaodereceitas.model.LoginApiResponse
import br.senai.sp.jandira.gestaodereceitas.model.RespostaHome
import br.senai.sp.jandira.gestaodereceitas.model.RespostaPerfil
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface CadastroService {

        @Headers("Content-Type: application/json")
        @POST("usuario")
        fun insert(@Body cadastro: Cadastro): Call<RespostaCadastro>

        @Headers("Content-Type: application/json")
        @POST("login")
        fun inserir(@Body login: Login): Call<LoginApiResponse>

        @Headers("Content-Type: application/json")
        @PUT("usuario")
        fun update(@Body recuperarSenha: RecuperarSenha): Call<RecuperarSenha>

        @Headers("Content-Type: application/json")
        @POST("receita")
        fun publicar(@Body receita: Receita): Call<RespostaReceita>

        @Headers("Content-Type: application/json")
        @GET("receita/usuario")
        fun listarReceitasDoUsuario(@Query("idUsuario") idUsuario: String): Call<RespostaHome>

        @Headers("Content-Type: application/json")
        @GET("receita/usuario")
        fun listarReceitasDoUsuarioPerfl(): Call<RespostaPerfil>

}