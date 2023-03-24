package net.itinajero.crudapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import net.itinajero.crudapp.model.Usuario

@Database(
    entities = [Usuario::class],
    version = 1
)
abstract class BaseDatos: RoomDatabase() {
    abstract fun usuariosDao(): UsuariosDao
}