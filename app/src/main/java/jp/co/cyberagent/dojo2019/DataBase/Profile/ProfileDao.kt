package jp.co.cyberagent.dojo2019.DataBase.Profile

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDao {
    @Query("SELECT * from profile_table")
    fun getAll(): LiveData<List<Profile>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(profile: Profile)

    @Query("UPDATE profile_table SET profile_name = :name , profile_gh = :gh , profile_tw = :tw where uid = :id")
    suspend fun update(id: Int, name: String, gh: String,tw: String)

    @Query("DELETE FROM profile_table WHERE uid IN (:id)")
    suspend fun delete(vararg id: Int)

    @Query("DELETE FROM profile_table")
    suspend fun deleteAll()
}