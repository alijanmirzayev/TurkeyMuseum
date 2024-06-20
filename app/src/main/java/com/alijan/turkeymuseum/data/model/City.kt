package com.alijan.turkeymuseum.data.model


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("cities")
    val cities: String?,
    @SerializedName("slug")
    val slug: String?
)