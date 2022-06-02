package com.tallercmovil.ejercicio3.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ProductApi {
    //https://www.serverbpw.com/cm/2022-2/products.php
    //https://www.serverbpw.com/cm/2022-2/product_detail.php?id=6541


    @GET
    fun getProduct(
        @Url url: String?
    ):Call<List<Product>>

    @GET("cm/2022-2/product_detail.php")
    fun getProductDetail(
        @Query("id") id: String?
    ): Call<ProductDetail>

}