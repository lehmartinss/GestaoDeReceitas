package br.senai.sp.jandira.gestaodereceitas.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val BASE_URL = "http://10.107.144.22:8080/v1/controle-receita/"

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCadastroService(): CadastroService {
        return retrofit.create(CadastroService::class.java)
    }

}
