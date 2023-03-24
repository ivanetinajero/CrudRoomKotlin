package net.itinajero.crudapp

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class UsuariosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    // Creamos las referencias a los elementos de nuestra vista
    val cvUsuario = itemView.findViewById<CardView>(R.id.cvUsuario)
    val tvUsuario = itemView.findViewById<TextView>(R.id.tvUsuario)
    val tvPais = itemView.findViewById<TextView>(R.id.tvPais)
    val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrar)

}