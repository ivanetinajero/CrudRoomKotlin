package net.itinajero.crudapp.dao

/**
    Database
    Con el siguiente codigo, se define una clase BaseDatos para contener la base de datos.
    BaseDatos define la configuracion de la base de datos y sirve como el punto de acceso principal de la app
    a los datos persistentes. La clase de la base de datos debe cumplir con las siguientes condiciones:

    1. La clase debe tener una anotación @Database que incluya un array entities que enumere todas las entidades de
       datos asociados con la base de datos.
    2. Debe ser una clase abstracta que extienda RoomDatabase.
    3. Para cada clase DAO que se asocio con la base de datos, esta base de datos debe definir un metodo abstracto que tenga cero argumentos y muestre una instancia de la clase DAO.

 */
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