package jp.co.cyberagent.dojo2019.DataBase.User

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * from user_table")
    fun getAll(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("UPDATE user_table SET user_name = :name , user_gh = :gh , user_tw = :tw where uid = :id")
    suspend fun update(id: Int, name: String, gh: String,tw: String)
}