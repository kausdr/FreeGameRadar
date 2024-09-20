package com.example.recyclerviewapp.model


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE name = :name AND password = :password LIMIT 1")
    fun getUser(name: String, password: String): User?

    @Delete
    fun delete(user: User)
}
