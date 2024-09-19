package com.example.recyclerviewapp.model


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): User?

    @Delete
    fun delete(user: User)
}
