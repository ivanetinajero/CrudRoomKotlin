package net.itinajero.crudapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class Usuario (

    @PrimaryKey var usuario: String,
    @ColumnInfo(name = "pais") var pais: String

)