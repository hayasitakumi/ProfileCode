package jp.co.cyberagent.dojo2019.Fragment

import android.net.Uri
import android.os.Bundle
import android.util.AndroidRuntimeException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import jp.co.cyberagent.dojo2019.DataBase.Word.WordViewModel
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.fragment_qrcode.*

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

        generate_button.setOnClickListener {
            try {
                val myname = "ranmaru"
                val twaccount = "syabonbubble"
                val ghaccount = "hayasitakumi"
                val size = 500

                val QRurl = Uri.Builder().scheme("ca-tech")
                    .authority("dojo").path("/share").appendQueryParameter("iam",myname)
                    .appendQueryParameter("tw",twaccount).appendQueryParameter("gh",ghaccount).build().toString()
                val barcodeEncoder = BarcodeEncoder()
                val bitmap = barcodeEncoder.encodeBitmap(QRurl, BarcodeFormat.QR_CODE, size, size)

                val imageViewQrCode = view.findViewById<View>(R.id.qrcode_imageView) as ImageView
                imageViewQrCode.setImageBitmap(bitmap)

            } catch (e: WriterException) {
                throw AndroidRuntimeException("Barcode Error.", e)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_qrcode, container, false)
    }
}