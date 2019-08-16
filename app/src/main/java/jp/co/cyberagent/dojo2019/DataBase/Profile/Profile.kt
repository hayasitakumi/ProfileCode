package jp.co.cyberagent.dojo2019.DataBase.Profile

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
class Profile {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo(index = true, name = "profile_position")
    var position: Int = 0

    @ColumnInfo(name = "profile_name")
    var name: String? = null

    @ColumnInfo(name = "profile_gh")
    var gh: String? = null

    @ColumnInfo(name = "profile_tw")
    var tw: String? = null
}