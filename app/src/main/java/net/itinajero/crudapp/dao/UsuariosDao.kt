package net.itinajero.crudapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import net.itinajero.crudapp.model.Usuario

@Dao
interface UsuariosDao {

    @Query("SELECT * FROM Usuarios")
    suspend fun obtenerUsuarios(): MutableList<Usuario>

    @Insert
    suspend fun agregarUsuario(usuario: Usuario)

    @Query("UPDATE Usuarios set pais=:paisParam WHERE usuario=:usuarioParam")
    suspend fun actualizarUsuario(usuarioParam: String, paisParam: String)

    @Query("DELETE FROM Usuarios WHERE usuario=:usuarioParam")
    suspend fun borrarUsuario(usuarioParam: String)

    /* MAS EJEMPLOS */

    /*
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
    */

}