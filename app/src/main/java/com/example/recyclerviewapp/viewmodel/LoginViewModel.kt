package com.example.recyclerviewapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.recyclerviewapp.model.UserSingleton
import com.example.recyclerviewapp.model.User
import com.example.recyclerviewapp.model.UserDao

class LoginViewModel(private val userDao: UserDao) : ViewModel() {
    var user: User? = null

    fun verifyUser(name: String, password: String): Boolean {
        val user = userDao.getUser(name, password)
        return user != null
    }

    fun addUser(user: User){
        UserSingleton.addUser(user)
        this.user = UserSingleton.user
    }


    fun deleteUser(user: User){
        UserSingleton.deleteUser(user)
        this.user = UserSingleton.user
    }
}
