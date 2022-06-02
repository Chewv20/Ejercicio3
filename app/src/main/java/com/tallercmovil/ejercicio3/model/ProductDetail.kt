package com.tallercmovil.ejercicio3.model

import com.google.gson.annotations.SerializedName

class ProductDetail {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("imag_url")
    var image: String? = null

    @SerializedName("desc")
    var desc: String? = null
}