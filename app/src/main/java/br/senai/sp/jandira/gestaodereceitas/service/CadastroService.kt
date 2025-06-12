package br.senai.sp.jandira.gestaodereceitas.service

<<<<<<< HEAD
import br.senai.sp.jandira.gestaodereceitas.model.*
=======
import br.senai.sp.jandira.gestaodereceitas.model.Cadastro
import br.senai.sp.jandira.gestaodereceitas.model.Login
import br.senai.sp.jandira.gestaodereceitas.model.Receita
import br.senai.sp.jandira.gestaodereceitas.model.RecuperarSenha
import br.senai.sp.jandira.gestaodereceitas.model.RespostaCadastro
import br.senai.sp.jandira.gestaodereceitas.model.RespostaReceita
import br.senai.sp.jandira.gestaodereceitas.model.LoginApiResponse
import br.senai.sp.jandira.gestaodereceitas.model.RespostaHome
import br.senai.sp.jandira.gestaodereceitas.model.RespostaPerfil
>>>>>>> cc93f028bfecf0e5b44d90b8e1ff1ec1f29b1ee6
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

<<<<<<< HEAD





}
=======
        @Headers("Content-Type: application/json")
        @GET("receita/usuario")
        fun listarReceitasDoUsuarioPerfl(): Call<RespostaPerfil>

}
>>>>>>> cc93f028bfecf0e5b44d90b8e1ff1ec1f29b1ee6
