package jp.co.cyberagent.dojo2019.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import jp.co.cyberagent.dojo2019.DataBase.WordViewModel
import jp.co.cyberagent.dojo2019.R

class QRcodeFragment : Fragment() {

    private val newWordActivityRequestCode = 1
    private lateinit var wordViewModel: WordViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
//
//
//        wordViewModel.allWords.observe(this, Observer { words ->
//            // Update the cached copy of the words in the adapter.
//            words?.let {
//                it.forEach {
//                    Log.d("TAG", it.word)
//                }
//            }
//        })

//        val db = Room.databaseBuilder(getActivity()!!.getApplicationContext(), AppDatabase::class.java, "database").build()
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