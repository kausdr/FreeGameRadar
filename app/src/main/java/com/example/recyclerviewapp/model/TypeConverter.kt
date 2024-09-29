package com.example.recyclerviewapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromListToString(filmes: List<String>?): String {
        return Gson().toJson(filmes)
    }

    @TypeConverter
    fun fromStringToList(filmesString: String?): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(filmesString, listType)
    }
}
