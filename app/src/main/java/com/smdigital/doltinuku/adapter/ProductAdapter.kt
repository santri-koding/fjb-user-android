package com.smdigital.doltinuku.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.model.ProductModel
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val itemList: ArrayList<ProductModel>)
    : RecyclerView.Adapter<ProductAdapter.HoldItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldItem {
        return HoldItem(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: HoldItem, position: Int) = holder.bindItem(itemList[position])

    class HoldItem(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(productModel: ProductModel) {
            itemView.tvTitleProduct.text = productModel.titleProduct
            itemView.tvPriceProduct.text = productModel.priceProduct
            itemView.ivProduct.setImageResource(productModel.imageCover)
        }
    }
}