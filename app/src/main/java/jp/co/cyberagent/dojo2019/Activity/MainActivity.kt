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
import jp.co.cyberagent.dojo2019.DataBase.Url
import jp.co.cyberagent.dojo2019.DataBase.UrlViewModel
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.Fragment.TabAdapter


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {

private lateinit var urlViewModel: UrlViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlViewModel = ViewModelProviders.of(this).get(UrlViewModel::class.java)

        val viewPager = findViewById<ViewPager>(R.id.main_viewPager)
        viewPager.adapter = TabAdapter(supportFragmentManager, this)
        val tabLayout = findViewById<TabLayout>(R.id.main_tabLayout)
        tabLayout.setupWithViewPager(viewPager)

        val uri = this.getIntent().data
        if (uri != null) {
            val url = Url()
            url.myname = Uri.parse(uri.toString()).getQueryParameter("iam")
            url.tw = Uri.parse(uri.toString()).getQueryParameter("tw")
            url.gh = Uri.parse(uri.toString()).getQueryParameter("gh")
            urlViewModel.insert(url)
        }

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
            val intent = Intent(this, QRscannerActivity::class.java)
            startActivity(intent)
        }
    }
}
