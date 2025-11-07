package com.sargis.khlopuzyan.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sargis.khlopuzyan.data.local.dao.UserDao
import com.sargis.khlopuzyan.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private const val DATABASE_NAME = "user_database"

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    DATABASE_NAME,
                )
//                    .fallbackToDestructiveMigrationOnDowngrade()
//                    .setQueryCoroutineContext(Dispatchers.IO)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun getTestInstance(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.inMemoryDatabaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}