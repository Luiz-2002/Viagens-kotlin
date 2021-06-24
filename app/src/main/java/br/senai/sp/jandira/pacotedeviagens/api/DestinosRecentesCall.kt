package br.senai.sp.jandira.pacotedeviagens.api

import br.senai.sp.jandira.pacotedeviagens.model.DestinosRecentes
import retrofit2.Call
import retrofit2.http.GET

interface DestinosRecentesCall {

    @GET("destinos/recentes")
    fun getDestinosRecentes() : Call<List<DestinosRecentes>>



}