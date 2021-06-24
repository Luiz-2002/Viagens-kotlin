package br.senai.sp.jandira.pacotedeviagens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.pacotedeviagens.R
import br.senai.sp.jandira.pacotedeviagens.model.Foto
import br.senai.sp.jandira.pacotedeviagens.ui.DialogImageDetail
import com.bumptech.glide.Glide

class GaleriaFotosDestinoAdapter(var context: Context) :
    RecyclerView.Adapter<GaleriaFotosDestinoAdapter.ViewHolder>() {

    private var listaDeFotos: List<Foto> = emptyList()

    fun updateListaDeFotos(lista: List<Foto>) {
        listaDeFotos = lista
        notifyDataSetChanged() //Atualiza o adapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.destino_fotos_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaDeFotos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foto = listaDeFotos[position]
        Glide.with(context).load(foto.url).into(holder.imageFoto)

        holder.imageFoto.setOnClickListener {
            val dialog = DialogImageDetail()
            dialog.updateImageUrl(foto.url)
            dialog.show((context as AppCompatActivity).supportFragmentManager, "Qualquer coisa")
        }
    }

    //inner class (Classes internas)
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageFoto = view.findViewById<ImageView>(R.id.image_foto)
    }
}