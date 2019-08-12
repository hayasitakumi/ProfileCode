package jp.co.cyberagent.dojo2019.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.Toast
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import jp.co.cyberagent.dojo2019.DataBase.Url
import jp.co.cyberagent.dojo2019.DataBase.UrlViewModel
import jp.co.cyberagent.dojo2019.DataBase.WordViewModel
import jp.co.cyberagent.dojo2019.R
import java.util.*


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
        val url = Url()

        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                startActivity(intent)
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()

                url.urlText = result.contents
                urlViewModel.insert(url)
                startActivity(intent)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}
