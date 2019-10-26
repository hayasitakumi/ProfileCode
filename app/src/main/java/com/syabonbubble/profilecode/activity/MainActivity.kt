package com.syabonbubble.profilecode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import com.syabonbubble.profilecode.R
import com.syabonbubble.profilecode.DataBase.MyViewModel
import com.syabonbubble.profilecode.DataBase.Profile.Profile
import com.syabonbubble.profilecode.Fragment.TabAdapter

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
            profile.position = profile.uid
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
