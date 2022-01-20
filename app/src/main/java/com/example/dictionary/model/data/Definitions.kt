package com.example.dictionary.model.data

import com.google.gson.annotations.SerializedName

data class Definitions(
    @SerializedName("definition") val definition: String,
    @SerializedName("example") val example: String,
    @SerializedName("synonyms") val synonyms: List<String>,
    @SerializedName("antonyms") val antonyms: List<String>
)
