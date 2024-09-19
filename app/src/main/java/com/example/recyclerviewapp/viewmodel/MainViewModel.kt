package com.example.recyclerviewapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerviewapp.model.City
import com.example.recyclerviewapp.model.Singleton

class MainViewModel :ViewModel() {
    val citiesLiveData = MutableLiveData<List<City>>()


    fun addCity(city: City){
        Singleton.addCity(city)
        citiesLiveData.value = Singleton.cities
    }


    fun deleteCity(position: Int){
        Singleton.deleteCity(position)
        citiesLiveData.value = Singleton.cities
    }


    fun updateCity(city: City){
        Singleton.updateCity(city)
        citiesLiveData.value = Singleton.cities
    }


    fun refresh(){
        citiesLiveData.value = Singleton.cities
    }
}