@file:Suppress("DEPRECATION")

package com.syabonbubble.profilecode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.syabonbubble.profilecode.R
import com.syabonbubble.profilecode.database.MyViewModel
import com.syabonbubble.profilecode.database.profile.Profile
import com.syabonbubble.profilecode.fragment.TabAdapter
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.Auth
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient

class MainActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private lateinit var profileViewModel: MyViewModel

    private lateinit var userName: String
    private lateinit var photoUrl: String
    private lateinit var googleApiClient: GoogleApiClient

    // Firebase instance variables
    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        profileViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        userName = ANONYMOUS

        googleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
            .addApi(Auth.GOOGLE_SIGN_IN_API)
            .build()


        val viewPager = main_viewPager
        viewPager.adapter = TabAdapter(supportFragmentManager)

        val tabLayout = main_tabLayout
        tabLayout.setupWithViewPager(viewPager)

        val uri = this.intent.data
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

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth!!.currentUser

        if (firebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        } else {
            userName = firebaseUser!!.displayName!!
            if (firebaseUser!!.photoUrl != null) {
                photoUrl = firebaseUser!!.photoUrl!!.toString()
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sign_out_menu -> {
                firebaseAuth!!.signOut()
                Auth.GoogleSignInApi.signOut(googleApiClient)
                userName = ANONYMOUS
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:$connectionResult")
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show()
    }

    companion object {

        private const val TAG = "MainActivity"
        const val ANONYMOUS = "anonymous"
    }
}
