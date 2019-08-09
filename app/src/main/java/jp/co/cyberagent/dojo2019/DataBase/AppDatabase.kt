package jp.co.cyberagent.dojo2019.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(
    Url::class,
    MyProfile::class
), version = 2)

abstract class AppDatabase : RoomDatabase() {

    // DAOを取得する。
    abstract fun urlDao(): UrlDao
    abstract fun myprofileDao(): MyProfileDao

}