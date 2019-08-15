package jp.co.cyberagent.dojo2019.Fragment


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class TabAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return ProfileFragment()
            }

            1 -> {
                return UserFragment()
            }
            else -> {
                return QRcodeFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val locale = Locale.getDefault()
        var friendText :String?
        var profileText :String?
        var qrcodeText :String?
        if(locale.equals(Locale.JAPAN)){
            friendText = "友達一覧"
            profileText = "プロフィール"
            qrcodeText = "QRコード"

        }else{
            friendText = "FRIEND"
            profileText = "PROFILE"
            qrcodeText = "QR CODE"
        }
        when (position) {
            0 -> {
                return friendText
            }
            1 -> {
                return profileText
            }
            else -> {
                return qrcodeText
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
}