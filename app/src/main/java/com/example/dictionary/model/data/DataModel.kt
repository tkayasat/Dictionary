package com.example.dictionary.model.data

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("word")
    val word: String,
    @SerializedName("phonetic")
    val phonetic: String,
    @SerializedName("phonetics")
    val phonetics: List<Phonetics>,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("meanings")
    val meanings: List<Meanings>
)