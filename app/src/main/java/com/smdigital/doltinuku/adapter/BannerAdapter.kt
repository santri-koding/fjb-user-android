package com.smdigital.doltinuku.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smdigital.doltinuku.CustomItemClickListener
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.model.BannerModel
import kotlinx.android.synthetic.main.item_banner.view.*

class BannerAdapter(con: Context?, banner: List<BannerModel>, private var listener: CustomItemClickListener) : PagerAdapter() {

    private var bannerModels: List<BannerModel> = banner
    private var inflater: LayoutInflater = LayoutInflater.from(con)

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = inflater.inflate(R.layout.item_banner, container, false)
        view.image_full.setImageResource(bannerModels[position].res)
        view.image_full.setOnClickListener { v -> listener.onItemClick(v, bannerModels[position].id) }
        container.addView(view, 0)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return bannerModels.size
    }

}