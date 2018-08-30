package com.smdigital.doltinuku.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.model.ProductModel
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedAdapter(private val feedItem: ArrayList<ProductModel>)
    : RecyclerView.Adapter<FeedAdapter.HoldFeed>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldFeed {
        return HoldFeed(LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false))
    }

    override fun getItemCount() = feedItem.size

    override fun onBindViewHolder(holder: HoldFeed, position: Int) = holder.bindItems(feedItem[position])

    class HoldFeed(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(productModel: ProductModel) {
            itemView.tvTitleFeed.text = productModel.titleProduct
            itemView.tvUserFeed.text = productModel.userName
            itemView.ivCoverFeed.setImageResource(productModel.imageCover)
            itemView.civPhoto.setImageResource(productModel.imageProfile)
        }
    }
}