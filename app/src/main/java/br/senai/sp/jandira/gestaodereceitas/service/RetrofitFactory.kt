package br.senai.sp.jandira.gestaodereceitas.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

<<<<<<< HEAD
    private val BASE_URL = "http://10.107.134.10:8080/v1/controle-receita/"
=======
    private val BASE_URL = "http://10.107.134.17:8080/v1/controle-receita/"
>>>>>>> cc93f028bfecf0e5b44d90b8e1ff1ec1f29b1ee6

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCadastroService(): CadastroService {
        return retrofit.create(CadastroService::class.java)
    }

}
