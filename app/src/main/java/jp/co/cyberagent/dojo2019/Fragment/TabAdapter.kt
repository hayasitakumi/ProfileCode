package jp.co.cyberagent.dojo2019.Fragment


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

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
        when (position) {
            0 -> {
                return "PROFILE LIST"
            }
            1 -> {
                return "MY PROFILE"
            }
            else -> {
                return "MY QRCODE"
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
}