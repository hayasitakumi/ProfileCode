package jp.co.cyberagent.dojo2019.DataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UrlDao {
    @Query("SELECT * from url_table")
    fun getAll(): LiveData<List<Url>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(url: Url)

    @Query("DELETE FROM url_table")
    suspend fun deleteAll()
}