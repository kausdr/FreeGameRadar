package com.example.recyclerviewapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerviewapp.model.User
import com.example.recyclerviewapp.model.UserDao
import com.example.recyclerviewapp.model.UserSingleton

class LoginViewModel(private val userDao: UserDao) : ViewModel() {
    var user: User? = null

    val filmeLiveData: LiveData<MutableList<String>>?
        get() = user?.filmes

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

    fun updateGames(newGames: MutableList<String>) {
        user?.let { currentUser ->
            currentUser.filmes.value?.apply {
                clear()
                addAll(newGames)
            }

            UserSingleton.updateFilmes(currentUser)
        }
    }

    fun deleteUser(user: User) {
        UserSingleton.deleteUser(user)
        this.user = null
    }
}
