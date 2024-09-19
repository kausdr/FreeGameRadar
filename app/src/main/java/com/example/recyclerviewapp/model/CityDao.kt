package com.example.recyclerviewapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CityDao {
    @Insert
    fun insert(city: City)

    @Query("SELECT * FROM cities")
    fun getAll(): List<City>

    @Query("SELECT * FROM cities WHERE id = :id")
    fun getById(id: Int): City

    @Update
    fun update(city: City)

    @Delete
    fun delete(city: City)
}