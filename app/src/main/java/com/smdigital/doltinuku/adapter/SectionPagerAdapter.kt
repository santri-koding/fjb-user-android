package com.smdigital.doltinuku.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.smdigital.doltinuku.fragment.MenuProfileFragment
import com.smdigital.doltinuku.fragment.MenuSellFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MenuProfileFragment()
            1 -> fragment = MenuSellFragment()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Profil"
            1 -> "Etalase"
            else -> {
                return ""
            }
        }
    }
}