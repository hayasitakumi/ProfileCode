package com.syabonbubble.profilecode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syabonbubble.profilecode.R
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
