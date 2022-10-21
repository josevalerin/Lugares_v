package com.lugares.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lugares.databinding.LugarFilaBinding
import com.lugares.model.Lugar
import com.lugares.ui.lugar.LugarFragmentDirections

class LugarAdapter : RecyclerView.Adapter<LugarAdapter.LugarViewHolder>(){

    //clase interna que se encarga de finalmente mostrar la informacion
    inner class LugarViewHolder(private val itemBinding: LugarFilaBinding)
        :RecyclerView.ViewHolder(itemBinding.root) {
        fun dibuja(lugar: Lugar) {
            itemBinding.tvNombre.text = lugar.nombre
            itemBinding.tvCorreo.text = lugar.correo
            itemBinding.tvTelefono.text = lugar.telefono
            itemBinding.vistaFila.setOnClickListener{
                //se cra una acccion para navegar a update pasadno un argumento lugar
                val action = LugarFragmentDirections
                    .actionNavLugarToUpdateLugarFragment(lugar)

                //afectivamente se pasa al fragemnto...
                itemView.findNavController().navigate(action)
            }

        }

    }
    //la lista donde estan los bojetos Lugar a dibujarse...
        private var listaLugares = emptyList<Lugar>()


// Esta funcion crea "cajitas" para cada lugar.. en memoria
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val itemBinding = LugarFilaBinding
            .inflate(LayoutInflater.from(parent.context),
                parent,
                false)
                return  LugarViewHolder(itemBinding)
    }
// esta funcion toma un lugar y lo envia a dibujar...
    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
    val lugar = listaLugares[position]
    holder.dibuja(lugar)
    }
// esta funcion devuelve la cantidad de elementos a dibujar...(cajitas)
    override fun getItemCount(): Int {
        return listaLugares.size
    }

    fun setListaLugares(lugares: List<Lugar>){
        this.listaLugares = lugares
        notifyDataSetChanged()
    }

}