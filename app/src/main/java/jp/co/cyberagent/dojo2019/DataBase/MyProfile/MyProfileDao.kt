//package jp.co.cyberagent.dojo2019.DataBase.MyProfile
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//
//@Dao
//interface MyProfileDao {
//    @Query("SELECT * FROM myprofile_table")
//    fun getAll(): LiveData<List<MyProfile>>
//
//    @Update
//    fun update(myprofile: MyProfile)
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insert(myprofile: MyProfile)
//
//    @Delete
//    fun delete(myprofile: MyProfile)
//}