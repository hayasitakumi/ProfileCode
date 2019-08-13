package jp.co.cyberagent.dojo2019.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val intent = getIntent()
        val url = intent.extras?.getString("key")

        webView.loadUrl(url)
    }
}
