package com.syabonbubble.profilecode.fragment


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ProfileFragment()
            }

            1 -> {
                UserFragment()
            }
            else -> {
                QRcodeFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val locale = Locale.getDefault()
        val friendText :String?
        val profileText :String?
        val qrcodeText :String?
        if(locale == Locale.JAPAN){
            friendText = "友達一覧"
            profileText = "プロフィール"
            qrcodeText = "QRコード"

        }else{
            friendText = "FRIEND"
            profileText = "PROFILE"
            qrcodeText = "QR CODE"
        }
        return when (position) {
            0 -> {
                friendText
            }
            1 -> {
                profileText
            }
            else -> {
                qrcodeText
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
}