package jp.co.cyberagent.dojo2019.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.DataBase.Profile.Profile
import jp.co.cyberagent.dojo2019.DataBase.Profile.ProfileViewModel
import jp.co.cyberagent.dojo2019.DataBase.User.User
import jp.co.cyberagent.dojo2019.DataBase.User.UserViewModel
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.fragment_listprofile.*
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    private lateinit var profileViewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        save_button.setOnClickListener {
            val input_user = User()

            input_user.name = "myname"
            input_user.gh = "github"
            input_user.tw = "twitter"

//            val profile = Profile()
//            profile.name = "name"
//            profile.gh = "gh"
//            profile.tw = "tw"
//            profileViewModel.insert(profile)
//            input_user.name = myname_text.text.toString()
//            input_user.gh = ghaccount_text.text.toString()
//            input_user.tw = twaccount_text.text.toString()

            userViewModel.insert(input_user)

        }
//        save_button.setOnLongClickListener{
//            Log.d("TAG", "long")
//            if (userViewModel.Userprofile != null) {
//                userViewModel.Userprofile.observe(this, Observer {
//                    if (it != null) {
//                        it.let {users ->
//                            users.forEach {user ->
//                                Log.d("TAG", "${user.name} / ${user.gh} / ${user.tw}")
//                            }
//
//                        }
//                    }
//                })
//            }else{
//                Log.d("TAG", "viewmodel is null")
//            }
//            true
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }
}