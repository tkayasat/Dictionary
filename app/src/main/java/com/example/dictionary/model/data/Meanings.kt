package com.example.dictionary.model.data

import com.google.gson.annotations.SerializedName

data class Meanings(
    @SerializedName("partOfSpeech")
    val partOfSpeech: String,
    @SerializedName("definitions")
    val definitions: List<Definitions>
)