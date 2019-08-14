package jp.co.cyberagent.dojo2019.DataBase.User

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo(name = "user_name")
    var name: String? = null

    @ColumnInfo(name = "user_gh")
    var gh: String? = null

    @ColumnInfo(name = "user_tw")
    var tw: String? = null
}