package jp.co.cyberagent.dojo2019.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.room.Room
import jp.co.cyberagent.dojo2019.DataBase.UrlViewModel
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.fragment_myprofile.*
import kotlin.concurrent.thread

class MyprofileFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        }

//            if (it == null) {
//                thread {
//                    db.myprofileDao().insert(my_profile)
//                }
//            }else{
//                thread {
//                    db.myprofileDao().update(my_profile)
//                }
//            }

//
//        save_button.setOnClickListener {
//            val my_profile = MyProfile()
//            my_profile.myName = view.findViewById<EditText>(R.id.myname_text).text.toString()
//            my_profile.ghAccount = view.findViewById<EditText>(R.id.ghaccount_text).text.toString()
//            my_profile.twAccount = view.findViewById<EditText>(R.id.twaccount_text).text.toString()


//            db.myprofileDao().loadAllaByIds(0).observe(this, androidx.lifecycle.Observer {
//                if(it.myName != null){
//
//                }
//            })

//            db.myprofileDao().getAll().observe(this, androidx.lifecycle.Observer {
//                // ユーザー一覧を取得した時やデータが変更された時に呼ばれる
//                if (it != null) {
//                    // TODO ユーザー一覧をRecyclerViewなどで表示
//                    it.forEach{
//                        Log.d("TAG", "${it.uid} / ${it.myName} / ${it.ghAccount} / ${it.twAccount}")
//                    }
//                }
//            })
//        }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_myprofile, container, false)
    }
}