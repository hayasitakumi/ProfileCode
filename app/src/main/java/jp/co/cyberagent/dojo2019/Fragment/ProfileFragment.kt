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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.Activity.WebActivity
import jp.co.cyberagent.dojo2019.DataBase.MyViewModel
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.fragment_listprofile.*


class ProfileFragment : Fragment(), ProfileAdapter.ProfileViewHolder.ItemClickListener {

    private lateinit var urlViewModel: MyViewModel

    val uids = mutableSetOf<Int>()
    val myname = mutableListOf<String>()
    val twaccount = mutableListOf<String>()
    val ghaccount = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        urlViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        return inflater.inflate(R.layout.fragment_listprofile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        urlViewModel.allProfiles.observe(this, Observer {
            if (it != null) {
                it.let { profiles ->
                    profiles.forEach { profile ->
                        Log.d("TAG", "${profile.uid} / ${profile.name}")
                        if (profile.uid in uids) {
                        } else {
                            Log.d("TAG", profile.uid.toString())
                            uids.add(profile.uid)
                            myname.add(profile.name.toString())
                            twaccount.add(profile.tw.toString())
                            ghaccount.add(profile.gh.toString())
                        }
                    }
                }
            }


            val itemTouchHelper = ItemTouchHelper(object :
                ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val fromPosition = viewHolder?.adapterPosition ?: 0
                    val toPosition = target?.adapterPosition ?: 0

                    profile_recyclerview.adapter?.notifyItemMoved(fromPosition, toPosition)

                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewHolder?.let {
                        Log.d("TAG", viewHolder.adapterPosition.toString())
                        urlViewModel.delete(uids.elementAt(viewHolder.adapterPosition))
                        uids.remove(viewHolder.adapterPosition)
                        myname.removeAt(viewHolder.adapterPosition)
                        twaccount.removeAt(viewHolder.adapterPosition)
                        ghaccount.removeAt(viewHolder.adapterPosition)
                        profile_recyclerview.adapter?.notifyItemRemoved(viewHolder.adapterPosition)
                    }
                }
            })
            itemTouchHelper.attachToRecyclerView(profile_recyclerview)

            profile_recyclerview.adapter = ProfileAdapter(view.context, this, myname, ghaccount, twaccount)
            profile_recyclerview.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        })
    }

    override fun onItemClick(view: View, position: Int) {
//        Toast.makeText(view.context, "position $position was tapped", Toast.LENGTH_SHORT).show()
//        Log.d("TAG", uids.elementAt(position).toString())
        val strList = arrayOf("githubアカウント：" + ghaccount.get(position), "twitterアカウント：" + twaccount.get(position))

        val intent = Intent(view.context, WebActivity::class.java)

        AlertDialog.Builder(view.context)
            .setTitle(myname.get(position))
            .setItems(strList) { _, which ->
                when (which) {
                    0 -> {
                        intent.putExtra("key", "https://github.com/" + ghaccount.get(position))
                        startActivity(intent)
                    }
                    1 -> {
                        intent.putExtra("key", "https://twitter.com/" + twaccount.get(position))
                        startActivity(intent)
                    }
                }
            }
            .setPositiveButton("キャンセル", { _, _ -> }).show()
    }
}