package jp.co.cyberagent.dojo2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import androidx.room.Room
import jp.co.cyberagent.dojo2019.DataBase.AppDatabase
import jp.co.cyberagent.dojo2019.DataBase.Url
import java.util.*
import kotlin.concurrent.thread


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.main_viewPager)
        viewPager.adapter = TabAdapter(supportFragmentManager, this)
        val tabLayout = findViewById<TabLayout>(R.id.main_tabLayout)
        tabLayout.setupWithViewPager(viewPager)

        main_camerabutton.setOnClickListener {
            val send_intent = Intent(this, QRscannerActivity::class.java)
            startActivity(send_intent)
        }
    }
}
