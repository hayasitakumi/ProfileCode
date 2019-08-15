package jp.co.cyberagent.dojo2019.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import jp.co.cyberagent.dojo2019.DataBase.MyViewModel
import jp.co.cyberagent.dojo2019.DataBase.Profile.Profile
import jp.co.cyberagent.dojo2019.R

class QRscannerActivity : AppCompatActivity() {

    private lateinit var profileViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscanner)
        profileViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
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
                val profile = Profile()
                profile.name = Uri.parse(result.contents.toString()).getQueryParameter("iam")
                profile.tw = Uri.parse(result.contents.toString()).getQueryParameter("tw")
                profile.gh = Uri.parse(result.contents.toString()).getQueryParameter("gh")
                profileViewModel.insert(profile)
                startActivity(intent)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
