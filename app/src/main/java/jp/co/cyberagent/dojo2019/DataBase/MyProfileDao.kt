//package jp.co.cyberagent.dojo2019.DataBase
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//
//@Dao
//interface MyProfileDao {
//
//    // シンプルなSELECTクエリ
//    @Query("SELECT * FROM myprofile")
//    fun getAll(): LiveData<List<MyProfile>>
//
//    @Update
//    fun update(myprofile: MyProfile)
//
//    // データモデルのクラスを引数に渡すことで、データの作成ができる。
//    @Insert
//    fun insert(myprofile: MyProfile)
//
//    // データモデルのクラスを引数に渡すことで、データの削除ができる。主キーでデータを検索して削除する場合。
//    @Delete
//    fun delete(myprofile: MyProfile)
//}