package com.tallercmovil.ejercicio3.view.iu.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.tallercmovil.ejercicio3.databinding.ActivityDetallesBinding
import com.tallercmovil.ejercicio3.model.ProductApi
import com.tallercmovil.ejercicio3.model.ProductDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Detalles : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding

    private val BASE_URL = "https://www.serverbpw.com/"
    private val LOGTAG = "LOGS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id", "0")

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gamesApi: ProductApi = retrofit.create(ProductApi::class.java)

        val call: Call<ProductDetail> = gamesApi.getProductDetail(id)

        call.enqueue(object: Callback<ProductDetail>{
            override fun onResponse(call: Call<ProductDetail>, response: Response<ProductDetail>) {
                with(binding){
                    pbConexion.visibility = View.INVISIBLE

                    tvNombre.text = response.body()?.name

                    Glide.with(this@Detalles)
                        .load(response.body()?.image)
                        .into(imageProducto)

                    tvDescripcion.text = response.body()?.desc
                }
            }

            override fun onFailure(call: Call<ProductDetail>, t: Throwable) {
                Log.d(LOGTAG, "Error")

                binding.pbConexion.visibility = View.VISIBLE

                Toast.makeText(this@Detalles, "No hay conexión", Toast.LENGTH_LONG).show()
            }

        })


    }
}