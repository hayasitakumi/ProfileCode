package jp.co.cyberagent.dojo2019

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.DataBase.Url
import kotlinx.android.synthetic.main.listprofile_row.view.*

class  ProfileViewAdapter(val names: List<String>, val ghs: List<String>, val tws:List<String>): RecyclerView.Adapter<ProfileViewAdapter.TagRecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.listprofile_row, parent, false)

        return TagRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: TagRecyclerViewHolder, position: Int) {
        holder.name.text = names.get(position)
        holder.gh.text = ghs.get(position)
        holder.tw.text = tws.get(position)
    }


    class TagRecyclerViewHolder(var view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.list_myname_text
        val gh:TextView = view.list_gh_text
        val tw:TextView = view.list_tw_text
    }
}