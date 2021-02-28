package com.example.hipo_projects.model

import com.google.gson.annotations.SerializedName

data class Position(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)