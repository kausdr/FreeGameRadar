package com.example.recyclerviewapp.model

import android.content.Context

object Singleton {
    lateinit var cities: List<City>

    lateinit var dao: CityDao

    fun init(context: Context) {

        CityDatabase.getInstance(context)?.apply {
            dao = cityDao()
            cities = dao.getAll()
        }

    }

    fun addCity(city: City) {
        dao.insert(city)
        cities = dao.getAll()
    }


    fun updateCity(city: City) {
        dao.update(city)
        cities = dao.getAll()
    }



    fun deleteCity(position: Int) {
        dao.delete(cities[position])
        cities = dao.getAll()
    }




}