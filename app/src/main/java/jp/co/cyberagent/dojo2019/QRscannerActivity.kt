package jp.co.cyberagent.dojo2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.Toast
import android.content.Intent
import androidx.room.Room
import jp.co.cyberagent.dojo2019.DataBase.AppDatabase
import jp.co.cyberagent.dojo2019.DataBase.Url
import java.util.*
import kotlin.concurrent.thread


class QRscannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscanner)

        IntentIntegrator(this).initiateScan()
    }

    // Get the results:
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        val intent = Intent(this, MainActivity::class.java)

        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                startActivity(intent)
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()

                val url = Url()
                url.uid = Random().nextInt()
                url.urlText = result.contents

//                thread {
//                    db.urlDao().insert(url)
//                }

                startActivity(intent)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
