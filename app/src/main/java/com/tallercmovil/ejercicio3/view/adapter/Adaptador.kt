package com.tallercmovil.ejercicio3.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tallercmovil.ejercicio3.databinding.ListElementBinding
import com.tallercmovil.ejercicio3.model.Product

class Adaptador(context: Context, games: List<Product>, onItemListener: OnItemListener): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    private val games = games
    private val mOnItemListener = onItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptador.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ListElementBinding.inflate(layoutInflater)

        return ViewHolder(binding, mOnItemListener)
    }

    override fun onBindViewHolder(holder: Adaptador.ViewHolder, position: Int) {
        holder.bindData(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }

    interface OnItemListener{
        fun onItemClick(producto: Product)
    }

    class ViewHolder(binding: ListElementBinding, onItemListener: OnItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        private val ivThumbnail = binding.imageProducto
        private val tvProducto = binding.tvProducto
        private val tvPrecio = binding.tvPrecio
        private val tvProveedor = binding.tvProveedor
        private val tvEnvio = binding.tvEnvio

        private val context = binding.root.context
        private val onItemListener = onItemListener
        private lateinit var producto: Product

        init{
            binding.root.setOnClickListener(this)
        }

        fun bindData(item: Product){

            tvProducto.text = item.nombre
            tvPrecio.text = "$"+item.precio
            tvProveedor.text = "De: "+item.proveedor
            if(item.envio.equals("0.00")){
                tvEnvio.text = "Gratis"
            }else{
                tvEnvio.text = "$"+item.envio
            }


            Glide.with(context)
                .load(item.image)
                .into(ivThumbnail)

            producto = item

        }

        override fun onClick(p0: View?) {
            onItemListener.onItemClick(producto)
        }


    }


}