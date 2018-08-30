package com.smdigital.doltinuku.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.adapter.FeedAdapter
import com.smdigital.doltinuku.model.ProductModel
import kotlinx.android.synthetic.main.fragment_feed.view.*

class FeedFragment : Fragment() {

    private var recyFedd: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        recyFedd = view.rvFeed

        recyFedd?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyFedd?.setHasFixedSize(true)

        itemFeed()
        return view
    }

    private fun itemFeed() {
        val allItemFeed = ArrayList<ProductModel>()
        allItemFeed.add(ProductModel(R.drawable.f2, R.drawable.user2, "John doe", "Fashion 2", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.a4, R.drawable.user5, "John doe", "Aksesoris 4", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.c4, R.drawable.user4, "John doe", "Kecantikan 4", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.r3, R.drawable.user1, "John doe", "Peralatan 3", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.f1, R.drawable.user2, "John doe", "Fashion 1", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.a2, R.drawable.user5, "John doe", "Aksesoris 2", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.a3, R.drawable.user5, "John doe", "Aksesoris 3", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.r5, R.drawable.user1, "John doe", "Peralatan 5", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.c1, R.drawable.user4, "John doe", "Kecantikan 1", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.f3, R.drawable.user2, "John doe", "Fashion 3", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.o2, R.drawable.user3, "John doe", "Otomotif 2", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.a1, R.drawable.user5, "John doe", "Aksesoris 1", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.e1, R.drawable.user1, "John doe", "Elektronik 1", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.e2, R.drawable.user1, "John doe", "Elektronik 2", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.c5, R.drawable.user4, "John doe", "Kecantikan 5", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.o1, R.drawable.user3, "John doe", "Otomotif 1", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.c3, R.drawable.user4, "John doe", "Kecantikan 3", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.e3, R.drawable.user1, "John doe", "Elektronik 3", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.f4, R.drawable.user2, "John doe", "Fashion 4", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.o3, R.drawable.user3, "John doe", "Otomotif 3", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.r1, R.drawable.user1, "John doe", "Peralatan 1", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.e4, R.drawable.user1, "John doe", "Elektronik 4", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.f5, R.drawable.user2, "John doe", "Fashion 5", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.o4, R.drawable.user3, "John doe", "Otomotif 4", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.r2, R.drawable.user1, "John doe", "Peralatan 2", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.a5, R.drawable.user5, "John doe", "Aksesoris 5", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.r5, R.drawable.user1, "John doe", "Peralatan 5", "Rp2.3Juta"))
        allItemFeed.add(ProductModel(R.drawable.c2, R.drawable.user4, "John doe", "Kecantikan 2", "Rp2.3Juta"))
        recyFedd?.adapter = FeedAdapter(allItemFeed)
    }


}
