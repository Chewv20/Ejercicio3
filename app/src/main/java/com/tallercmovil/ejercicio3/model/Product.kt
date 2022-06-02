package com.tallercmovil.ejercicio3.model

import com.google.gson.annotations.SerializedName

class Product {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var nombre: String? = null

    @SerializedName("thumbnail_url")
    var image: String? = null

    @SerializedName("price")
    var precio: String? = null

    @SerializedName("provider")
    var proveedor: String? = null

    @SerializedName("delivery")
    var envio: String? = null


}