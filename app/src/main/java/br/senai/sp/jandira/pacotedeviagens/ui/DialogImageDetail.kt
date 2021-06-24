package br.senai.sp.jandira.pacotedeviagens.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import br.senai.sp.jandira.pacotedeviagens.R
import com.bumptech.glide.Glide

class DialogImageDetail : DialogFragment() {

    lateinit var image: ImageView
    lateinit var btnFechar: ImageButton

    private var imageUrl: String = ""

    fun updateImageUrl(imageUrl: String) {
        this.imageUrl = imageUrl
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.customDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_foto_detail, container, false)

        image = view.findViewById(R.id.img_foto)
        btnFechar = view.findViewById(R.id.imgbutton_close)

        btnFechar.setOnClickListener {
            this.dismiss()
        }

        Glide.with(context!!).load(imageUrl).into(image)

        return view
    }
}