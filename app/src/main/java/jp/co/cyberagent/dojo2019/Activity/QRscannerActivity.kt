package jp.co.cyberagent.dojo2019.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import jp.co.cyberagent.dojo2019.DataBase.Url
import jp.co.cyberagent.dojo2019.DataBase.UrlViewModel
import jp.co.cyberagent.dojo2019.R


class QRscannerActivity : AppCompatActivity() {


    private val newWordActivityRequestCode = 1
    private lateinit var urlViewModel: UrlViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscanner)

        urlViewModel = ViewModelProviders.of(this).get(UrlViewModel::class.java)

        urlViewModel.allUrls.observe(this, androidx.lifecycle.Observer { urls ->
            urls.let {
                it.forEach{
                    Log.d("TAG", "${it.uid} / ${it.urlText}")
                }
            }
        })

        IntentIntegrator(this).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        val intent = Intent(this, MainActivity::class.java)

        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                startActivity(intent)
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()

                val url = Url()
                val str = result.contents.toString()
                val urlstr = Uri.parse(str)
                val test = urlstr.toString()
                Log.d("TAG", test)
                url.urlText = Uri.parse(result.contents.toString()).toString()
                urlViewModel.insert(url)
                startActivity(intent)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
