package com.smdigital.doltinuku.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.smdigital.doltinuku.R
import kotlinx.android.synthetic.main.fragment_promo.view.*

class PromoFragment : Fragment() {

    var bgs = intArrayOf(R.drawable.promotion, R.drawable.shoe, R.drawable.smartphone, R.drawable.tv)

    companion object {
        private val ARG_SECTION_NUMBER = "section_number"
        fun newInstance(sectionNumber: Int): PromoFragment {
            val fragment = PromoFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_promo, container, false)
        rootView.imagePromo.setBackgroundResource(bgs[arguments?.getInt(ARG_SECTION_NUMBER)!!])
        return rootView
    }
}