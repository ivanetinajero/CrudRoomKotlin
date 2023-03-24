package net.itinajero.crudapp

import net.itinajero.crudapp.model.Usuario

interface AdaptadorListener {

    fun onEditItemClick(usuario: Usuario)
    fun onDeleteItemClick(usuario: Usuario)

}