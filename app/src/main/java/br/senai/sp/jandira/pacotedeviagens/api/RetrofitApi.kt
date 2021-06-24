package br.senai.sp.jandira.pacotedeviagens.api

import br.senai.sp.jandira.pacotedeviagens.api.UrlApi.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    companion object {
        fun getRetrofit(): Retrofit {

            var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit

        }
    }
}