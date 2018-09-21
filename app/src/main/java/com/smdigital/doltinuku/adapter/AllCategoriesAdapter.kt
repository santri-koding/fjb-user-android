package com.smdigital.doltinuku.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.smdigital.doltinuku.GlideApp
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.model.ProductModel
import kotlinx.android.synthetic.main.item_list_category.view.*

class AllCategoriesAdapter(private val itemCat: ArrayList<ProductModel>, private val mListener: (ProductModel) -> Unit)
    : RecyclerView.Adapter<AllCategoriesAdapter.HoldCat>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldCat {
        return HoldCat(LayoutInflater.from(parent.context).inflate(R.layout.item_list_category, parent, false))
    }

    override fun getItemCount() = itemCat.size

    override fun onBindViewHolder(holder: HoldCat, position: Int) = holder.bindItem(itemCat[position], mListener)

    class HoldCat(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(productModel: ProductModel, mListener: (ProductModel) -> Unit) {
            itemView.tvCatTitle.text = productModel.titleProduct
            itemView.tvCatPrice.text = productModel.priceProduct
            GlideApp.with(itemView.context).load(productModel.imageCover).centerCrop().transition(withCrossFade()).into(itemView.ivCatCover)
            itemView.setOnClickListener { mListener(productModel) }
        }
    }
}