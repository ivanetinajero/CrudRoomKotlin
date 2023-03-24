package net.itinajero.crudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.coroutines.launch
import net.itinajero.crudapp.databinding.ActivityMainBinding
import net.itinajero.crudapp.dao.BaseDatos
import net.itinajero.crudapp.model.Usuario

/**
 * https://www.youtube.com/watch?v=gztLyRShg2I&t=1154s
 */
class MainActivity : AppCompatActivity(), AdaptadorListener {

    lateinit var binding: ActivityMainBinding
    var listaUsuarios: MutableList<Usuario> = mutableListOf()
    lateinit var adaptador: AdaptadorUsuarios
    // Instancia de la base de datos
    lateinit var room: BaseDatos
    // Este objeto lo usaremos editar / borrar
    lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuracion del RecyclerView
        binding.rvUsuarios.layoutManager = LinearLayoutManager(this)
        // Aqui creamos la instancia de nuestra base de datos
        room = Room.databaseBuilder(this, BaseDatos::class.java, "dbProyecto" ).build()

        // Al momento de iniciarse la aplicacion mostramos los usuarios existentes en la base de datos.
        obtenerUsuarios(room)

        binding.btnAddUpdate.setOnClickListener {
            // 1. Primero revisamos que el formulario este completo
            if(binding.etUsuario.text.isNullOrEmpty() || binding.etPais.text.isNullOrEmpty()) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                // Salimos de este metodo
                return@setOnClickListener
            }

            // 2. Verificamos si vamos a agregar un usuario dependiendo del texto del boton.
            if (binding.btnAddUpdate.text.equals("agregar")) {
                usuario = Usuario(
                    binding.etUsuario.text.toString().trim(),
                    binding.etPais.text.toString().trim()
                )
                agregarUsuario(room, usuario)
            }

            // 3. Verificamos si vamos a actualizar un usuario dependiendo del texto del boton.
            if(binding.btnAddUpdate.text.equals("actualizar")) {
                usuario.pais = binding.etPais.text.toString().trim()
                actualizarUsuario(room, usuario)
            }

        }
    }

    private fun obtenerUsuarios(room: BaseDatos) {
        lifecycleScope.launch {
            listaUsuarios = room.usuariosDao().obtenerUsuarios()
            adaptador = AdaptadorUsuarios(listaUsuarios, this@MainActivity)
            binding.rvUsuarios.adapter = adaptador
        }
    }

    fun agregarUsuario(room: BaseDatos, usuario: Usuario) {
        lifecycleScope.launch {
            room.usuariosDao().agregarUsuario(usuario)
            obtenerUsuarios(room)
            limpiarCampos()
        }
    }

    fun actualizarUsuario(room: BaseDatos, usuario: Usuario) {
        lifecycleScope.launch {
            room.usuariosDao().actualizarUsuario(usuario.usuario, usuario.pais)
            obtenerUsuarios(room)
            limpiarCampos()
        }
    }

    fun limpiarCampos() {
        usuario.usuario = ""
        usuario.pais = ""
        binding.etUsuario.setText("")
        binding.etPais.setText("")

        if (binding.btnAddUpdate.text.equals("actualizar")) {
            binding.btnAddUpdate.setText("agregar")
            binding.etUsuario.isEnabled = true
        }

    }

    override fun onEditItemClick(usuario: Usuario) {
        binding.btnAddUpdate.setText("actualizar")
        binding.etUsuario.isEnabled = false
        this.usuario = usuario
        binding.etUsuario.setText(this.usuario.usuario)
        binding.etPais.setText(this.usuario.pais)
    }

    override fun onDeleteItemClick(usuario: Usuario) {
        lifecycleScope.launch {
            room.usuariosDao().borrarUsuario(usuario.usuario)
            adaptador.notifyDataSetChanged()
            obtenerUsuarios(room)
        }
    }

}