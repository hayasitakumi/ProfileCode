package com.syabonbubble.profilecode.Fragment

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
import com.syabonbubble.profilecode.DataBase.MyViewModel
import com.syabonbubble.profilecode.R
import kotlinx.android.synthetic.main.fragment_qrcode.*

class QRcodeFragment : Fragment() {

    private val newWordActivityRequestCode = 1

    private lateinit var userViewModel: MyViewModel

    var myname: String = ""
    var twaccount: String = ""
    var ghaccount: String = ""
    val size = 500

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        return inflater.inflate(R.layout.fragment_qrcode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        customFont()

        try {
            userViewModel.allUsers.observe(this, Observer {
                if (it != null) {
                    it.let { users ->
                        users.forEach { user ->

                            user_myname_text.text = user.name
                            user_gh_text.text = user.gh
                            user_tw_text.text = user.tw
//                            Log.d("TAG", "${user.name}/${user.gh}/${user.tw}")
                            myname = user.name.toString()
                            twaccount = user.tw.toString()
                            ghaccount = user.gh.toString()

                            val QRurl = Uri.Builder().scheme("ca-tech")
                                .authority("dojo").path("/share").appendQueryParameter("iam", myname)
                                .appendQueryParameter("tw", twaccount).appendQueryParameter("gh", ghaccount).build().toString()
                            val barcodeEncoder = BarcodeEncoder()
                            val bitmap = barcodeEncoder.encodeBitmap(QRurl, BarcodeFormat.QR_CODE, size, size)

                            val imageViewQrCode = view.findViewById<View>(R.id.qrcode_imageView) as ImageView
                            imageViewQrCode.setImageBitmap(bitmap)
                        }
                    }
                }
            })
        } catch (e: WriterException) {
            throw AndroidRuntimeException("Barcode Error.", e)
        }
    }
    fun customFont(){
        now_text?.setTypeface(Typeface.createFromAsset(now_text.context.assets, "KosugiMaru-Regular.ttf"))
        user_myname_text?.setTypeface(Typeface.createFromAsset(user_gh_text.context.assets, "KosugiMaru-Regular.ttf"))
        user_gh_text?.setTypeface(Typeface.createFromAsset(user_gh_text.context.assets, "Sofia-Regular.ttf"))
        user_tw_text?.setTypeface(Typeface.createFromAsset(user_tw_text.context.assets, "KosugiMaru-Regular.ttf"))
    }
}