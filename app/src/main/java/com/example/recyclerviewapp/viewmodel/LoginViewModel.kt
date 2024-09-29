package com.example.recyclerviewapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerviewapp.api.MovieResponse
import com.example.recyclerviewapp.api.RetrofitInstance
import com.example.recyclerviewapp.model.Filme
import com.example.recyclerviewapp.model.User
import com.example.recyclerviewapp.model.UserDao
import com.example.recyclerviewapp.model.UserSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val userDao: UserDao) : ViewModel() {
    var user: User? = null

    val filmeLiveData: List<String>?
        get() = user?.filmes

    private val _filmesList = MutableLiveData<MutableList<Filme>>()
    val filmesList: LiveData<MutableList<Filme>> get() = _filmesList

    fun verifyUser(name: String, password: String): Boolean {
        val user = userDao.getUser(name, password)
        if (user != null) {
            this.user = user
            return true
        }
        return false
    }

    fun addUser(user: User) {
        UserSingleton.addUser(user)
        this.user = UserSingleton.user
    }

    fun updateFilmes(newFilmes: List<String>) {
        user?.let {
            it.filmes = newFilmes
        }
    }

    fun deleteUser(user: User) {
        UserSingleton.deleteUser(user)
        this.user = null
    }

    fun fetchPopularMovies(apiKey: String) {
        RetrofitInstance.api.getPopularMovies(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { movieResponse ->
                        _filmesList.postValue(movieResponse.results.toMutableList())
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _filmesList.postValue(mutableListOf())
            }
        })
    }
}
