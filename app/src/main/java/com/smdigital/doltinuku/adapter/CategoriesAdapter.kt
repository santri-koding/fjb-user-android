package com.smdigital.doltinuku.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.model.CategoriesModel
import kotlinx.android.synthetic.main.item_categories.view.*

class CategoriesAdapter(private val itemCat: ArrayList<CategoriesModel>, private val mListener: (CategoriesModel) -> Unit)
    : RecyclerView.Adapter<CategoriesAdapter.HoldCat>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldCat {
        return HoldCat(LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false))
    }

    override fun getItemCount() = itemCat.size

    override fun onBindViewHolder(holder: HoldCat, position: Int) = holder.bindItem(itemCat[position], mListener)

    class HoldCat(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(categoriesModel: CategoriesModel, mListener: (CategoriesModel) -> Unit) {
            itemView.textViewCat.text = categoriesModel.title
            itemView.imageViewCat.setImageResource(categoriesModel.img)
            itemView.setOnClickListener { mListener(categoriesModel) }
        }
    }
}