package jp.co.cyberagent.dojo2019.DataBase

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Url::class], version = 1)
abstract class UrlRoomDatabase : RoomDatabase() {

    abstract fun urlDao(): UrlDao

    companion object {
        @Volatile
        private var INSTANCE: UrlRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): UrlRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UrlRoomDatabase::class.java,
                    "url_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance

                instance
            }
        }
    }
}
