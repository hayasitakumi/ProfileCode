package jp.co.cyberagent.dojo2019

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import jp.co.cyberagent.dojo2019.DataBase.AppDatabase
import jp.co.cyberagent.dojo2019.DataBase.Url
import kotlinx.android.synthetic.main.fragment_listprofile.*
import kotlin.concurrent.thread

class ListprofileFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val db = Room.databaseBuilder(view!!.context, AppDatabase::class.java, "database").build()


//        val titles = ArrayList<String>()

        val myname = mutableListOf("name1", "name2", "name3")
        val ghaccount = mutableListOf("gh1", "", "gh3")
        val twaccount = mutableListOf("tw1", "tw2", "tw3")

        profile_cardview.adapter = ProfileViewAdapter(myname, ghaccount, twaccount)
        profile_cardview.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

//        db.urlDao().getAll().observe(this, Observer<List<Url>> {
//            // ユーザー一覧を取得した時やデータが変更された時に呼ばれる
//            if (it != null) {
//                // TODO ユーザー一覧をRecyclerViewなどで表示
//
//                it.forEach {
//                    titles.add(it.urlText.toString())
////                    Log.d("TAG", it.urlText)
//                }
//            }
//        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listprofile, container, false)
    }
}