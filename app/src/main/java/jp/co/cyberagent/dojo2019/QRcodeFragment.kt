package jp.co.cyberagent.dojo2019

import android.os.Bundle
import android.util.AndroidRuntimeException
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.room.Room
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import jp.co.cyberagent.dojo2019.DataBase.AppDatabase
import jp.co.cyberagent.dojo2019.DataBase.Url
import kotlinx.android.synthetic.main.fragment_qrcode.*
import kotlin.concurrent.thread

class QRcodeFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = Room.databaseBuilder(getActivity()!!.getApplicationContext(), AppDatabase::class.java, "database").build()
//
//        db.myprofileDao().getAll().observe(this, androidx.lifecycle.Observer {
//            // ユーザー一覧を取得した時やデータが変更された時に呼ばれる
//            if (it != null) {
//                // TODO ユーザー一覧をRecyclerViewなどで表示
//                it.forEach {
//                    Log.d("TAG", it.myName)
//                    Log.d("TAG", it.ghAccount)
//                    Log.d("TAG", it.twAccount)
//                }
//            }
//        })

//        generate_button.setOnClickListener {
//            try {
//                val myname = "MYNAME"
//                val twaccount = "TWACCOUNT"
//                val ghaccount = "GHACCOUNT"
//                val size = 500
//
//                val QRurl = "ca-tech://dojo/share?iam=" + myname + "%20Kagurazaka&tw=" + twaccount + "&gh=" + ghaccount
//
//                val barcodeEncoder = BarcodeEncoder()
//                //QRコードをBitmapで作成
//                val bitmap = barcodeEncoder.encodeBitmap(QRurl, BarcodeFormat.QR_CODE, size, size)
//
//                //作成したQRコードを画面上に配置
//                val imageViewQrCode = view.findViewById<View>(R.id.qrcode_imageView) as ImageView
//                imageViewQrCode.setImageBitmap(bitmap)
//
//            } catch (e: WriterException) {
//                throw AndroidRuntimeException("Barcode Error.", e)
//            }
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_qrcode, container, false)
    }
}