package jp.co.cyberagent.dojo2019.DataBase

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Url::class], version = 1)
abstract class UrlRoomDatabase : RoomDatabase() {

    abstract fun urlDao(): UrlDao

    companion object {
        @Volatile
        private var INSTANCE: UrlRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): UrlRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UrlRoomDatabase::class.java,
                    "url_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(UrlDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }

        private class UrlDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.urlDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(urlDao: UrlDao) {

        }
    }

}
