package com.example.recyclerviewapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.recyclerviewapp.model.UserSingleton
import com.example.recyclerviewapp.model.User

class LoginViewModel() : ViewModel() {
    var user: User? = null



    fun addUser(user: User){
        UserSingleton.addUser(user)
        this.user = UserSingleton.user
    }


    fun deleteUser(user: User){
        UserSingleton.deleteUser(user)
        this.user = UserSingleton.user
    }
}
