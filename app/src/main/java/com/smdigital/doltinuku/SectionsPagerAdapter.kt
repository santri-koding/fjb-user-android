package com.smdigital.doltinuku

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.smdigital.doltinuku.fragment.PromoFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return PromoFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return 5
    }
}