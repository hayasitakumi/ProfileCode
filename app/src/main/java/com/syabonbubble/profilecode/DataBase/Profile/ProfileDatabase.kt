package com.syabonbubble.profilecode.DataBase.Profile

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Profile::class], version = 1)
abstract class ProfileDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile
        private var INSTANCE: ProfileDatabase? = null

        fun getDatabase(
            context: Context
        ): ProfileDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProfileDatabase::class.java,
                    "profile_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance

                instance
            }
        }
    }
}