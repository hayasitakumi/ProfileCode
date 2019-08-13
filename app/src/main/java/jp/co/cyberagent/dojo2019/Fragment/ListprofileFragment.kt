package jp.co.cyberagent.dojo2019.Fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.Activity.MainActivity
import jp.co.cyberagent.dojo2019.Activity.WebActivity
import jp.co.cyberagent.dojo2019.DataBase.UrlViewModel
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.fragment_listprofile.*

class ListprofileFragment : Fragment(), ProfileAdapter.ProfileViewHolder.ItemClickListener {

    private lateinit var urlViewModel: UrlViewModel


    val uids = mutableSetOf<Int>()
    val myname = mutableListOf<String>()
    val twaccount = mutableListOf<String>()
    val ghaccount = mutableListOf<String>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        // uriに「hoge-scheme://hoge-host?id=hogehogehoge」が入る
//        val uri = activity?.getIntent()?.data
//        // パラメータで指定したhogehogehogeが取得できる
//        val id = uri?.getQueryParameter("id")
//        if (id != null) {
//            Log.d("TAG", id)
//        } else {
//            Log.d("TAG", "null")
//        }

        urlViewModel = ViewModelProviders.of(this).get(UrlViewModel::class.java)

        urlViewModel.allUrls.observe(this, Observer {
            if (it != null) {
                it?.let { urls ->
                    urls.forEach { url ->
//                        if (url != null) {
                            if (url.uid in uids) {
                            } else {
                                uids.add(url.uid)

                                val separate = url.urlText?.split("ca-tech://dojo/share?iam=", "%20", "&tw=", "&gh=")
//                                if(url != null){
//                                    myname.add(url.urlText?.getQueryParameter("iam").toString())
//                                    twaccount.add(url.urlText?.getQueryParameter("tw").toString())
//                                    ghaccount.add(url.urlText?.getQueryParameter("gh").toString())
//                                }
                                if (separate != null) {
                                    myname.add(separate.get(1))
                                    twaccount.add(separate.get(3))
                                    ghaccount.add(separate.get(4))
                                }
                            }
//                        }

                    }
                }
            }
            profile_cardview.adapter = ProfileAdapter(view.context, this, myname, ghaccount, twaccount)
            profile_cardview.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listprofile, container, false)
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(view.context, "position $position was tapped", Toast.LENGTH_SHORT).show()

        val strList = arrayOf(
            "名前：" + myname.get(position),
            "githubアカウント：" + ghaccount.get(position), "twitterアカウント：" + twaccount.get(position)
        )

        val intent = Intent(view.context, WebActivity::class.java)

        // dialogの表示
        AlertDialog.Builder(view.context) // FragmentではActivityを取得して生成
            .setTitle("リスト選択ダイアログ")
            .setItems(strList) { dialog, which ->
                when (which) {
                    1 -> {
                        intent.putExtra("key", "https://github.com/" + ghaccount.get(position))
                        startActivity(intent)
                    }
                    2 -> {
                        intent.putExtra("key", "https://twitter.com/" + twaccount.get(position))
                        startActivity(intent)
                    }
                }
            }
            .setPositiveButton("キャンセル", { dialog, which -> }).show()

    }
}