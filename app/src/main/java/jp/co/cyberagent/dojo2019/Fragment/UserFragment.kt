package jp.co.cyberagent.dojo2019.Fragment

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import jp.co.cyberagent.dojo2019.DataBase.MyViewModel
import jp.co.cyberagent.dojo2019.DataBase.User.User
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {
    private lateinit var userViewModel: MyViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        customFont()

        save_button.setOnClickListener{

            userViewModel.delete()
            val input_user = User()

            input_user.name = my_name_edittext.text.toString()
            input_user.gh = my_gh_edittext.text.toString()
            input_user.tw = my_tw_edittext.text.toString()

            userViewModel.insert(input_user)
        }
    }

    fun customFont(){
        my_name_text?.setTypeface(Typeface.createFromAsset(my_name_text.context.assets, "MPLUSRounded1c-Bold.ttf"))
        my_gh_text?.setTypeface(Typeface.createFromAsset(my_gh_text.context.assets, "MPLUSRounded1c-Bold.ttf"))
        my_tw_text?.setTypeface(Typeface.createFromAsset(my_tw_text.context.assets, "MPLUSRounded1c-Bold.ttf"))
        my_name_edittext?.setTypeface(Typeface.createFromAsset(my_name_edittext.context.assets, "MPLUSRounded1c-Bold.ttf"))
        my_gh_edittext?.setTypeface(Typeface.createFromAsset(my_gh_edittext.context.assets, "MPLUSRounded1c-Bold.ttf"))
        my_tw_edittext?.setTypeface(Typeface.createFromAsset(my_tw_edittext.context.assets, "MPLUSRounded1c-Bold.ttf"))
        save_button?.setTypeface(Typeface.createFromAsset(save_button.context.assets, "MPLUSRounded1c-Bold.ttf"))
    }
}