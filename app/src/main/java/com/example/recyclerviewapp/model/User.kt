package com.example.recyclerviewapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var password: String
)
