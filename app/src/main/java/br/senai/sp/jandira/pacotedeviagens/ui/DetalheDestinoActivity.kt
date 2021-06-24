package br.senai.sp.jandira.pacotedeviagens.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.pacotedeviagens.R
import br.senai.sp.jandira.pacotedeviagens.adapter.GaleriaFotosDestinoAdapter
import br.senai.sp.jandira.pacotedeviagens.api.FotoCall
import br.senai.sp.jandira.pacotedeviagens.api.RetrofitApi
import br.senai.sp.jandira.pacotedeviagens.model.DestinosRecentes
import br.senai.sp.jandira.pacotedeviagens.model.Foto
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalheDestinoActivity : AppCompatActivity() {

    lateinit var ivFotoDestino: ImageView
    lateinit var tvLocal: TextView
    lateinit var tvValor: TextView
    lateinit var tvTextoDescricao: TextView
    lateinit var tvApartirDe: TextView
    lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    lateinit var rvGaleriaFotosDestino: RecyclerView
    lateinit var galeriaFotosDestinoAdapter: GaleriaFotosDestinoAdapter
    lateinit var destinoRecente: DestinosRecentes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_destino)

        carregarDados()
        carregarListaDeFotos()
    }

    private fun carregarListaDeFotos() {

        rvGaleriaFotosDestino = findViewById(R.id.rv_galeria_fotos_destino)
        galeriaFotosDestinoAdapter = GaleriaFotosDestinoAdapter(this)
        rvGaleriaFotosDestino.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvGaleriaFotosDestino.adapter = galeriaFotosDestinoAdapter

        // Lista de fotos que preencherão a recycleView
        var fotos: List<Foto> = emptyList()

        //Instancia o Retrofit
        val retrofit = RetrofitApi.getRetrofit()

        //Fazer chamada para a Interface
        val fotosCall = retrofit.create(FotoCall::class.java)
        val call = fotosCall.getFotoDestino(destinoRecente.id)

        //Executa a requisição para API
        call.enqueue(object : Callback<List<Foto>> {
            override fun onFailure(call: Call<List<Foto>>, t: Throwable) {
                Toast.makeText(this@DetalheDestinoActivity, "Falhou!!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Foto>>, response: Response<List<Foto>>) {
                fotos = response.body()!!
                galeriaFotosDestinoAdapter.updateListaDeFotos(fotos)
            }
        })
    }

    private fun carregarDados() {
        ivFotoDestino = findViewById(R.id.iv_destino)
        tvLocal = findViewById(R.id.tv_local)
        tvValor = findViewById(R.id.tv_valor)
        tvTextoDescricao = findViewById(R.id.tv_texto_descricao)
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        tvApartirDe = findViewById(R.id.tv_a_partir_de)

        destinoRecente =
            intent.getSerializableExtra("destino") as DestinosRecentes

        tvLocal.text = "${destinoRecente.nomeCidade} - ${destinoRecente.siglaEstado}"

        if (destinoRecente.valor == 0.0) {
            tvValor.text = "GRÁTIS"
            tvApartirDe.text = ""
        } else {
            tvValor.text = "R$ ${String.format("%.2f", destinoRecente.valor)}"
        }

        tvTextoDescricao.text = destinoRecente.descricao
        collapsingToolbarLayout.title = destinoRecente.nome

        Glide.with(this).load(destinoRecente.urlFoto).into(ivFotoDestino)
    }
}