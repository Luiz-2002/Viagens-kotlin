package br.senai.sp.jandira.pacotedeviagens.api

import br.senai.sp.jandira.pacotedeviagens.model.Foto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FotoCall {

    @GET("destinos/{id}/fotos")
    fun getFotoDestino(@Path("id") id: Long): Call<List<Foto>>
}