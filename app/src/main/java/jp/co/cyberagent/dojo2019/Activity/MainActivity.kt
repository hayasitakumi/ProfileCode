package jp.co.cyberagent.dojo2019.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import jp.co.cyberagent.dojo2019.DataBase.MyViewModel
import jp.co.cyberagent.dojo2019.DataBase.Profile.Profile
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.Fragment.TabAdapter
import kotlinx.android.synthetic.main.listprofile_row.*
import android.graphics.Typeface
import kotlinx.android.synthetic.main.fragment_user.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {

private lateinit var profileViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        profileViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        val viewPager = main_viewPager
        viewPager.adapter = TabAdapter(supportFragmentManager, this)

        val tabLayout = main_tabLayout
        tabLayout.setupWithViewPager(viewPager)

        val uri = this.getIntent().data
        if (uri != null) {
            val profile = Profile()
            profile.name = Uri.parse(uri.toString()).getQueryParameter("iam")
            profile.tw = Uri.parse(uri.toString()).getQueryParameter("tw")
            profile.gh = Uri.parse(uri.toString()).getQueryParameter("gh")
            profileViewModel.insert(profile)
        }

        main_camerabutton.setOnClickListener {
            val intent = Intent(this, QRscannerActivity::class.java)
            startActivity(intent)
        }
    }
}
