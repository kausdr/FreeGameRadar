package com.example.recyclerviewapp.model

import android.content.Context
import com.example.recyclerviewapp.model.Singleton.cities
import com.example.recyclerviewapp.model.Singleton.dao

object UserSingleton {
    var user: User? = null
    private lateinit var userDao: UserDao

    fun init(context: Context) {
        UserDatabase.getInstance(context)?.apply {
            userDao = userDao()
            user = userDao.getUser()
        }
    }

//    fun getUser(): User? {
//        return user
//    }

    fun addUser(newUser: User) {
        userDao.insert(newUser)
        user = newUser
    }

    fun deleteUser(user: User) {

        userDao.delete(user)
    }
}
