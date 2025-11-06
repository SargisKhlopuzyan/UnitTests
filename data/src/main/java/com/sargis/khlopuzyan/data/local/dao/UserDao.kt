package com.sargis.khlopuzyan.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sargis.khlopuzyan.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao {

    @Query("SELECT * FROM user")
    abstract fun getUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE id = :id")
    abstract suspend fun getUserById(id: Int): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUser(node: UserEntity): Long

    @Delete
    abstract suspend fun deleteUser(node: UserEntity): Int
}