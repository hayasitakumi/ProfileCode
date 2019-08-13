//package jp.co.cyberagent.dojo2019.DataBase.MyProfile
//
//import jp.co.cyberagent.dojo2019.DataBase.Word.Word
//import jp.co.cyberagent.dojo2019.DataBase.Word.WordDao
//
//import androidx.sqlite.db.SupportSQLiteDatabase
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import android.content.Context
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
//@Database(entities = [Word::class], version = 1)
//abstract class WordRoomDatabase : RoomDatabase() {
//
//    abstract fun wordDao(): WordDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: WordRoomDatabase? = null
//
//        fun getDatabase(
//            context: Context,
//            scope: CoroutineScope
//        ): WordRoomDatabase {
//
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    WordRoomDatabase::class.java,
//                    "word_database"
//                )
//                    .fallbackToDestructiveMigration()
//                    .addCallback(
//                        WordDatabaseCallback(
//                            scope
//                        )
//                    )
//                    .build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//
//        private class WordDatabaseCallback(
//            private val scope: CoroutineScope
//        ) : RoomDatabase.Callback() {
//
//            override fun onOpen(db: SupportSQLiteDatabase) {
//                super.onOpen(db)
//                INSTANCE?.let { database ->
//                    scope.launch {
//                        populateDatabase(database.wordDao())
//                    }
//                }
//            }
//        }
//
//        suspend fun populateDatabase(wordDao: WordDao) {
//            // Start the app with a clean database every time.
//            // Not needed if you only populate on creation.
////            wordDao.deleteAll()
////
////            var word = Word("Hello")
////            wordDao.insert(word)
////            word = Word("World!")
////            wordDao.insert(word)
//        }
//    }
//
//}
