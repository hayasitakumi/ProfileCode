package jp.co.cyberagent.dojo2019.Activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jp.co.cyberagent.dojo2019.DataBase.UrlViewModel
import jp.co.cyberagent.dojo2019.DataBase.Word
import jp.co.cyberagent.dojo2019.DataBase.WordViewModel
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.Fragment.TabAdapter


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {

//    private lateinit var urlViewModel: UrlViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.main_viewPager)
        viewPager.adapter = TabAdapter(supportFragmentManager, this)
        val tabLayout = findViewById<TabLayout>(R.id.main_tabLayout)
        tabLayout.setupWithViewPager(viewPager)
//
//        urlViewModel = ViewModelProviders.of(this).get(UrlViewModel::class.java)
//
//        urlViewModel.allUrls.observe(this, Observer { urls ->
//            if (urls != null) {
//                urls?.let {
//                    it.forEach {
//                        Log.d("TAG", "${it.uid} / ${it.urlText}")
//                    }
//                }
//            }
//        })

        main_camerabutton.setOnClickListener {
            val send_intent = Intent(this, QRscannerActivity::class.java)
            startActivity(send_intent)
        }
    }
}
