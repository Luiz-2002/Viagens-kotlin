package br.senai.sp.jandira.pacotedeviagens.model

import com.google.gson.annotations.SerializedName

data class Foto(
    var id: Long,
    var url: String,
    var destaque: Boolean
)