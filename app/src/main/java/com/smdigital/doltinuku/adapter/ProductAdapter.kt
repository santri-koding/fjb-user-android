package com.smdigital.doltinuku.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.smdigital.doltinuku.GlideApp
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.model.ProductModel
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val itemList: ArrayList<ProductModel>, private val mListener: (ProductModel) -> Unit)
    : RecyclerView.Adapter<ProductAdapter.HoldItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldItem {
        return HoldItem(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: HoldItem, position: Int) = holder.bindItem(itemList[position], mListener)

    inner class HoldItem(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(productModel: ProductModel, mListener: (ProductModel) -> Unit) {
            itemView.tvTitleProduct.text = productModel.titleProduct
            itemView.tvPriceProduct.text = productModel.priceProduct
            GlideApp.with(itemView.context).load(productModel.imageCover).centerCrop().transition(withCrossFade()).into(itemView.ivProduct)
            itemView.setOnClickListener { mListener(productModel) }
        }
    }
}