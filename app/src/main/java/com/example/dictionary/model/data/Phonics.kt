package com.example.dictionary.model.data

import com.google.gson.annotations.SerializedName

data class Phonetics(
    @SerializedName("text")
    val text: String,
    @SerializedName("audio")
    val audio: String
)