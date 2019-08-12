package jp.co.cyberagent.dojo2019.Fragment


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import jp.co.cyberagent.dojo2019.Fragment.ListprofileFragment
import jp.co.cyberagent.dojo2019.Fragment.MyprofileFragment
import jp.co.cyberagent.dojo2019.Fragment.QRcodeFragment

class TabAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return MyprofileFragment()
            }

            1 -> {
                return QRcodeFragment()
            }
            else -> {
                return ListprofileFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "MY PROFILE"
            }
            1 -> {
                return "MY QRCODE"
            }
            else -> {
                return "PROFILE LIST"
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
}