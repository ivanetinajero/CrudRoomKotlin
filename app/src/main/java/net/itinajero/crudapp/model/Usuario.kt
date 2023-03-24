package net.itinajero.crudapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class Usuario (

    // Si necesitas que Room asigne ID automáticos a instancias de entidades, configura la propiedad autoGenerate de @PrimaryKey en true.
    //@PrimaryKey(true) val uid: Int,
    @PrimaryKey var usuario: String,
    @ColumnInfo(name = "pais") var pais: String

    // Ignorar campos no deseados de la entidad
    //@Ignore val picture: Bitmap?

)