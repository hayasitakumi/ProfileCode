package jp.co.cyberagent.dojo2019.DataBase.MyProfile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myprofile_table")
class MyProfile {
    @PrimaryKey
    @ColumnInfo(name = "my_name")
    var myName: String = ""

    @ColumnInfo(name = "gh_account")
    var ghAccount: String? = null

    @ColumnInfo(name = "tw_account")
    var twAccount: String? = null
}