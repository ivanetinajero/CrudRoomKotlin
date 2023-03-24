package net.itinajero.crudapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.itinajero.crudapp.model.Usuario

class AdaptadorUsuarios (

    private val listaUsuarios: MutableList<Usuario>,
    private val listener: AdaptadorListener
    ): RecyclerView.Adapter<UsuariosViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_usuario, parent, false)
        return UsuariosViewHolder(vista)
    }

    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
        val usuario = listaUsuarios[position]

        holder.tvUsuario.text = usuario.usuario
        holder.tvPais.text = usuario.pais

        holder.cvUsuario.setOnClickListener {
            listener.onEditItemClick(usuario)
        }

        holder.btnBorrar.setOnClickListener {
            listener.onDeleteItemClick(usuario)
        }
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }
}