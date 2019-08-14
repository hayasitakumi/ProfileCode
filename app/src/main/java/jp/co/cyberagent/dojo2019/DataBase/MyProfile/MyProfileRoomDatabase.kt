package jp.co.cyberagent.dojo2019.DataBase.MyProfile

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [MyProfile::class], version = 1)
abstract class MyProfileRoomDatabase : RoomDatabase() {

    abstract fun myprofileDao(): MyProfileDao

    companion object {
        @Volatile
        private var INSTANCE: MyProfileRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): MyProfileRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyProfileRoomDatabase::class.java,
                    "myprofile_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
