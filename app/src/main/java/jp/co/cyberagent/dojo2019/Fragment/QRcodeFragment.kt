package jp.co.cyberagent.dojo2019.Fragment

import android.net.Uri
import android.os.Bundle
import android.util.AndroidRuntimeException
import android.util.Log
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
import jp.co.cyberagent.dojo2019.DataBase.MyViewModel
import jp.co.cyberagent.dojo2019.R

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

        try {
            userViewModel.allUsers.observe(this, Observer {
                if (it != null) {
                    it.let { users ->
                        users.forEach { user ->
                            Log.d("TAG", "${user.name}/${user.gh}/${user.tw}")
                            myname = user.name.toString()
                            twaccount = user.gh.toString()
                            ghaccount = user.tw.toString()

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
}