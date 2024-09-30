package com.example.recyclerviewapp.model

import android.content.Context


object UserSingleton {
    var user: User? = null
    lateinit var userDao: UserDao

    fun init(context: Context) {
        UserDatabase.getInstance(context)?.apply {
            userDao = userDao()
        }
    }

    fun addUser(newUser: User) {
        userDao.insert(newUser)
        user = newUser
    }


    fun updateFilmes(user: User) {
        userDao.update(user)
    }

    fun deleteUser(user: User) {
        userDao.delete(user)
    }

    fun getUserById(userId: Int): User? {
        user = userDao.getUserById(userId)
        return user
    }
}

