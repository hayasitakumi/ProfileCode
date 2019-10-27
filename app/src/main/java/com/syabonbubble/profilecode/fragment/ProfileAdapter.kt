package com.syabonbubble.profilecode.fragment

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syabonbubble.profilecode.R
import kotlinx.android.synthetic.main.listprofile_row.view.*

class ProfileAdapter(
    val context: Context, private val itemClickListener: ProfileViewHolder.ItemClickListener,
    private val names: List<String>, private val ghs: List<String>, private val tws: List<String>
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    private var mRecyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }


    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mView = layoutInflater.inflate(R.layout.listprofile_row, parent, false)

        mView.setOnClickListener { view ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }

        return ProfileViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.name.text = names[position]
        holder.name.typeface = Typeface.createFromAsset(holder.name.context.assets, "KosugiMaru-Regular.ttf")
        holder.gh.text = ghs[position]
        holder.gh.typeface = Typeface.createFromAsset(holder.gh.context.assets, "Sofia-Regular.ttf")
        holder.tw.text = tws[position]
        holder.tw.typeface = Typeface.createFromAsset(holder.tw.context.assets, "KosugiMaru-Regular.ttf")
    }


    class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.list_myname_text
        val gh: TextView = view.list_gh_text
        val tw: TextView = view.list_tw_text

        interface ItemClickListener {
            fun onItemClick(view: View, position: Int)
        }
    }
}