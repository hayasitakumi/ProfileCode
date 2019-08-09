package jp.co.cyberagent.dojo2019.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UrlDao {

    // シンプルなSELECTクエリ
    @Query("SELECT * FROM url")
    fun getAll(): LiveData<List<Url>>


    // メソッドの引数をSQLのパラメーターにマッピングするには :引数名 と書く
    @Query("SELECT * FROM url WHERE uid IN (:urlIds)")
    fun loadAllaByIds(vararg urlIds: Int): LiveData<Url>

    // 複数の引数も渡せる
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): Url

    // データモデルのクラスを引数に渡すことで、データの作成ができる。
    @Insert
    fun insert(url: Url)

    // 可変長引数にしたり
    @Insert
    fun insertAll(vararg urls: Url)

    // Listで渡したりもできる
    @Insert
    fun insertAll(urls: List<Url>)

    // データモデルのクラスを引数に渡すことで、データの削除ができる。主キーでデータを検索して削除する場合。
    @Delete
    fun delete(url: Url)

    // 複雑な条件で削除したい場合は、@Queryを使ってSQLを書く
//    @Query("DELETE FROM url WHERE age < :age")
//    fun deleteYoungerThan(age: Int)
}