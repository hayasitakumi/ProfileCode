package jp.co.cyberagent.dojo2019.Fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Typeface
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
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.Activity.WebActivity
import jp.co.cyberagent.dojo2019.DataBase.MyViewModel
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.fragment_listprofile.*
import java.util.*
import kotlin.collections.ArrayList


class ProfileFragment : Fragment(), ProfileAdapter.ProfileViewHolder.ItemClickListener {

    private lateinit var profileViewModel: MyViewModel

    val uids = mutableSetOf<Int>()
    val myname = mutableListOf<String>()
    val twaccount = mutableListOf<String>()
    val ghaccount = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        profileViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        return inflater.inflate(R.layout.fragment_listprofile, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profileViewModel.allProfiles.observe(this, Observer {
            if (it != null) {
                it.let { profiles ->
                    profiles.forEach { profile ->
                        if (profile.uid in uids) {
                        } else {
                            Log.d(
                                "TAG",
                                "${profile.uid} / ${profile.position} / ${profile.name} / ${profile.tw} / ${profile.gh}"
                            )
                            uids.add(profile.uid)
                            myname.add(profile.name.toString())
                            twaccount.add(profile.tw.toString())
                            ghaccount.add(profile.gh.toString())
                        }
                    }

                }
            }
            profile_recyclerview.adapter = ProfileAdapter(view.context, this, myname, ghaccount, twaccount)
            profile_recyclerview.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        })


        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT
            ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder?.adapterPosition ?: 0
                val toPosition = target?.adapterPosition ?: 0

                profile_recyclerview.adapter?.notifyItemMoved(fromPosition, toPosition)

                val tmpname = myname[fromPosition]
                val tmptw = twaccount[fromPosition]
                val tmpgh = ghaccount[fromPosition]



                myname.removeAt(fromPosition)
                myname.add(toPosition, tmpname)

                twaccount.removeAt(fromPosition)
                twaccount.add(toPosition, tmptw)

                ghaccount.removeAt(fromPosition)
                ghaccount.add(toPosition, tmpgh)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewHolder?.let {
                    Log.d("TAG", "${uids.elementAt(viewHolder.adapterPosition)} / ${viewHolder.adapterPosition} / ${direction}" )
                    profileViewModel.delete(uids.elementAt(viewHolder.adapterPosition))
                    uids.remove(viewHolder.adapterPosition)
                    myname.removeAt(viewHolder.adapterPosition)
                    twaccount.removeAt(viewHolder.adapterPosition)
                    ghaccount.removeAt(viewHolder.adapterPosition)
                    profile_recyclerview.adapter?.notifyItemRemoved(viewHolder.adapterPosition)
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val alpha = 1.0f - Math.abs(dX) / viewHolder.itemView.width
                    viewHolder.itemView.alpha = alpha
                    viewHolder.itemView.translationX = dX
                } else {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if (actionState == ACTION_STATE_DRAG) {
                    viewHolder?.itemView?.alpha = 0.5f
                }
            }

            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.alpha = 1.0f
            }
        })
        itemTouchHelper.attachToRecyclerView(profile_recyclerview)

    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(view.context, "position $position was tapped", Toast.LENGTH_SHORT).show()
        Log.d("TAG", uids.elementAt(position).toString())
        val locale = Locale.getDefault()
        val itemList = arrayOfNulls<String>(2)
        var canselText: String?

        if (locale.equals(Locale.JAPAN)) {
            itemList[0] = "Githubアカウント：" + ghaccount.get(position)
            itemList[1] = "Twitterアカウント：" + twaccount.get(position)
            canselText = "キャンセル"
        } else {
            itemList[0] = "Github Account：" + ghaccount.get(position)
            itemList[1] = "Twitter Account：" + twaccount.get(position)
            canselText = "CANSEL"
        }
        val intent = Intent(view.context, WebActivity::class.java)

        AlertDialog.Builder(view.context)
            .setTitle(myname.get(position))
            .setItems(itemList) { _, which ->
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
            .setPositiveButton(canselText, { _, _ -> }).show()
    }
}