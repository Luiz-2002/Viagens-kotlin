package br.senai.sp.jandira.pacotedeviagens.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.pacotedeviagens.R
import br.senai.sp.jandira.pacotedeviagens.model.DestinosRecentes
import br.senai.sp.jandira.pacotedeviagens.ui.DetalheDestinoActivity
import com.bumptech.glide.Glide

class DestinoRecenteAdapter(val context: Context) :
    RecyclerView.Adapter<DestinoRecenteAdapter.Holder>() {

    var listRecentes = emptyList<DestinosRecentes>()

    fun updateListaRecente(lista: List<DestinosRecentes>) {
        listRecentes = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.card_recentes, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listRecentes.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val destinosRecentes = listRecentes[position]

        holder.tvNomeDestino.text = destinosRecentes.nome
        holder.tvLocalidade.text = destinosRecentes.nomeCidade

        if (destinosRecentes.valor <= 0) {
            holder.tvValor.text = "Free | Grátis"
        } else {
            holder.tvValor.text = "R$ ${String.format("%.2f", destinosRecentes.valor)}"
        }

        if(destinosRecentes.urlFoto.trim().isNotEmpty()){
            Glide.with(context)
                .load(destinosRecentes.urlFoto)
                .into(holder.ivFotoCapa)
        }

        holder.cardDestino.setOnClickListener {
            val intent = Intent(context, DetalheDestinoActivity::class.java)
            intent.putExtra("destino", destinosRecentes)
            context.startActivity(intent)
        }

    }

    // inner class
    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        val tvNomeDestino = view.findViewById<TextView>(R.id.tv_nome_destino)
        val tvLocalidade = view.findViewById<TextView>(R.id.tv_localidade)
        val tvValor = view.findViewById<TextView>(R.id.tv_valor)
        val ivFotoCapa = view.findViewById<ImageView>(R.id.iv_destinos_recentes)
        val cardDestino = view.findViewById<CardView>(R.id.card_destino)

    }
}