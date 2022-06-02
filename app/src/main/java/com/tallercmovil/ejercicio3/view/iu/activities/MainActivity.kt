package com.tallercmovil.ejercicio3.view.iu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tallercmovil.ejercicio3.R
import com.tallercmovil.ejercicio3.databinding.ActivityMainBinding
import com.tallercmovil.ejercicio3.model.Product
import com.tallercmovil.ejercicio3.model.ProductApi
import com.tallercmovil.ejercicio3.view.adapter.Adaptador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),Adaptador.OnItemListener {

    private val LOGTAG = "LOGS"
    private val BASE_URL = "https://www.serverbpw.com/"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productApi: ProductApi = retrofit.create(ProductApi::class.java)

        val call: Call<List<Product>> = productApi.getProduct("cm/2022-2/products.php")

        call.enqueue(object: Callback<List<Product>>{
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {

                binding.pbConexion.visibility = View.INVISIBLE

                val adaptador = Adaptador(this@MainActivity,response.body()!!,this@MainActivity)

                val recyclerView = binding.rvMenu

                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                recyclerView.adapter = adaptador


            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d(LOGTAG,getString(R.string.error))
                Toast.makeText(this@MainActivity,getString(R.string.noConection), Toast.LENGTH_LONG).show()
                binding.pbConexion.visibility = View.INVISIBLE
            }

        })

    }

    override fun onItemClick(producto: Product) {
        val parametros = Bundle()
        parametros.putString("id", producto.id)
        val intent = Intent(this,Detalles::class.java)

        intent.putExtras(parametros)

        startActivity(intent)
    }
}