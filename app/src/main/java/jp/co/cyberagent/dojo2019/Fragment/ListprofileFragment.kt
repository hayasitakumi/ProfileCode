package jp.co.cyberagent.dojo2019.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import jp.co.cyberagent.dojo2019.DataBase.UrlViewModel
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.fragment_listprofile.*

class ListprofileFragment : Fragment() {

    private lateinit var urlViewModel: UrlViewModel


    val uids = mutableSetOf<Int>()
    val myname = mutableListOf<String>()
    val twaccount = mutableListOf<String>()
    val ghaccount = mutableListOf<String>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        urlViewModel = ViewModelProviders.of(this).get(UrlViewModel::class.java)

        urlViewModel.allUrls.observe(this, Observer { urls ->
            if (urls != null) {
                urls?.let {
                    it.forEach {
                        //                        Log.d("TAG", "${it.uid} / ${it.urlText}")

                        if(it.uid in uids){}
                        else{
                            uids.add(it.uid)

                            val separate = it.urlText?.split("ca-tech://dojo/share?iam=", "%20", "&tw=", "&gh=")

                            if (separate != null) {
                                myname.add(separate.get(1))
                                twaccount.add(separate.get(3))
                                ghaccount.add(separate.get(4))
                            }
                        }
                    }
                }
            }
//            Log.d("TAG", "name:${myname.toString()}")
//            Log.d("TAG", "tw:${twaccount.toString()}")
//            Log.d("TAG", "gh:${ghaccount.toString()}")
            profile_cardview.adapter = ProfileViewAdapter(myname, ghaccount, twaccount)
            profile_cardview.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        })
//
//        profile_cardview.adapter = ProfileViewAdapter(myname, ghaccount, twaccount)
//        profile_cardview.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)

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