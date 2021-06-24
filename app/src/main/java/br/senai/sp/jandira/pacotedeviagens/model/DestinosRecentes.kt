package br.senai.sp.jandira.pacotedeviagens.model

import java.io.Serializable

data class DestinosRecentes (
    var id: Long = 0,
    var nome: String = "",
    var valor: Double = 0.0,
    var descricao: String = "",
    var nomeCidade: String = "",
    var siglaEstado: String = "",
    var urlFoto: String = ""
) : Serializable