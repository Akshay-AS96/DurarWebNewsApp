package com.durarweb.newsapp.model

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id: Any? = null,
    @SerializedName("name")
    val name: String? = null
)