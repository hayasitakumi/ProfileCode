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
import jp.co.cyberagent.dojo2019.Activity.WebActivity
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.fragment_listprofile.*
import jp.co.cyberagent.dojo2019.DataBase.Profile.ProfileViewModel


class ListprofileFragment : Fragment(), ProfileAdapter.ProfileViewHolder.ItemClickListener {

    private lateinit var urlViewModel: ProfileViewModel



    val uids = mutableSetOf<Int>()
    val myname = mutableListOf<String>()
    val twaccount = mutableListOf<String>()
    val ghaccount = mutableListOf<String>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        urlViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        urlViewModel.allProfiles.observe(this, Observer {
            if (it != null) {
                it.let { profiles ->
                    profiles.forEach { profile ->
                        Log.d("TAG", "${profile.uid} / ${profile.name}")
                        if (profile.uid in uids) {
                        } else {
                            uids.add(profile.uid)
                            myname.add(profile.name.toString())
                            twaccount.add(profile.tw.toString())
                            ghaccount.add(profile.gh.toString())
                        }
                    }
                }
            }
            profile_recyclerview.adapter = ProfileAdapter(view.context, this, myname, ghaccount, twaccount)
            profile_recyclerview.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listprofile, container, false)
    }

    override fun onItemClick(view: View, position: Int) {
//        Toast.makeText(view.context, "position $position was tapped", Toast.LENGTH_SHORT).show()
//        Log.d("TAG", uids.elementAt(position).toString())
        val strList = arrayOf(
            "名前：" + myname.get(position),
            "githubアカウント：" + ghaccount.get(position), "twitterアカウント：" + twaccount.get(position)
        )

        val intent = Intent(view.context, WebActivity::class.java)

        AlertDialog.Builder(view.context) // FragmentではActivityを取得して生成
            .setTitle("リスト選択ダイアログ")
            .setItems(strList) { _, which ->
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
            .setPositiveButton("キャンセル", { _, _ -> }).show()
    }

    override fun onItemLongClick(view: View, position: Int) {
        urlViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        urlViewModel.delete(uids.elementAt(position))
//        urlViewModel.update(position + 1, "fullname", "github", "twitter")
        Toast.makeText(view.context, "Long tapped", Toast.LENGTH_SHORT).show()
    }
}