package com.syabonbubble.profilecode.fragment

import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.util.AndroidRuntimeException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.syabonbubble.profilecode.database.MyViewModel
import com.syabonbubble.profilecode.R
import kotlinx.android.synthetic.main.fragment_qrcode.*

class QRcodeFragment : Fragment() {

    private lateinit var userViewModel: MyViewModel

    private var myname: String = ""
    private var twaccount: String = ""
    private var ghaccount: String = ""
    private val size = 500

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        return inflater.inflate(R.layout.fragment_qrcode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        customFont()

        try {
            userViewModel.allUsers.observe(this, Observer {
                it?.let { users ->
                    users.forEach { user ->

                        user_myname_text.text = user.name
                        user_gh_text.text = user.gh
                        user_tw_text.text = user.tw
            //                            Log.d("TAG", "${user.name}/${user.gh}/${user.tw}")
                        myname = user.name.toString()
                        twaccount = user.tw.toString()
                        ghaccount = user.gh.toString()

                        val qrUrl = Uri.Builder().scheme("ca-tech")
                            .authority("dojo").path("/share").appendQueryParameter("iam", myname)
                            .appendQueryParameter("tw", twaccount).appendQueryParameter("gh", ghaccount).build().toString()
                        val barcodeEncoder = BarcodeEncoder()
                        val bitmap = barcodeEncoder.encodeBitmap(qrUrl, BarcodeFormat.QR_CODE, size, size)

                        val imageViewQrCode = view.findViewById<View>(R.id.qrcode_imageView) as ImageView
                        imageViewQrCode.setImageBitmap(bitmap)
                    }
                }
            })
        } catch (e: WriterException) {
            throw AndroidRuntimeException("Barcode Error.", e)
        }
    }
    private fun customFont(){
        now_text?.typeface = Typeface.createFromAsset(now_text.context.assets, "KosugiMaru-Regular.ttf")
        user_myname_text?.typeface = Typeface.createFromAsset(user_gh_text.context.assets, "KosugiMaru-Regular.ttf")
        user_gh_text?.typeface = Typeface.createFromAsset(user_gh_text.context.assets, "Sofia-Regular.ttf")
        user_tw_text?.typeface = Typeface.createFromAsset(user_tw_text.context.assets, "KosugiMaru-Regular.ttf")
    }
}