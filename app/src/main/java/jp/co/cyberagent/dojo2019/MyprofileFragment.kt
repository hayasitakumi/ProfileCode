package jp.co.cyberagent.dojo2019

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.room.Room
import jp.co.cyberagent.dojo2019.DataBase.AppDatabase
import jp.co.cyberagent.dojo2019.DataBase.MyProfile
import jp.co.cyberagent.dojo2019.DataBase.Url
import kotlinx.android.synthetic.main.fragment_listprofile.*
import kotlinx.android.synthetic.main.fragment_myprofile.*
import java.util.*
import kotlin.concurrent.thread

class MyprofileFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val db =
            Room.databaseBuilder(getActivity()!!.getApplicationContext(), AppDatabase::class.java, "database").build()

        val my_profile = MyProfile()


        db.myprofileDao().getAll().observe(this, androidx.lifecycle.Observer {
            it.forEach{
                Log.d("TAG", "${it.myName} / ${it.ghAccount} / ${it.twAccount}")
            }
        })

        save_button.setOnClickListener {
            my_profile.myName = view.findViewById<EditText>(R.id.myname_text).text.toString()
            my_profile.ghAccount = view.findViewById<EditText>(R.id.ghaccount_text).text.toString()
            my_profile.twAccount = view.findViewById<EditText>(R.id.twaccount_text).text.toString()


            thread {
                db.myprofileDao().insert(my_profile)
            }
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


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_myprofile, container, false)
    }
}