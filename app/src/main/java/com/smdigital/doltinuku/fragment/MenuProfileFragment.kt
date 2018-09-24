package com.smdigital.doltinuku.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.activity.DetailActivity
import com.smdigital.doltinuku.adapter.ProductAdapter
import com.smdigital.doltinuku.helper.StartSnapHelper
import com.smdigital.doltinuku.model.ProductModel
import kotlinx.android.synthetic.main.fragment_menu_profile.view.*

class MenuProfileFragment : Fragment() {

    private var recyFavSaya: RecyclerView? = null
    private var recyTerakhirHub: RecyclerView? = null
    private var recyBarusanKom: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_menu_profile, container, false)

        recyFavSaya = rootView.rvFavSaya
        recyTerakhirHub = rootView.rvTerakhirHub
        recyBarusanKom = rootView.rvBarusanKomen

        recyFavSaya?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyFavSaya?.isNestedScrollingEnabled = false
        recyFavSaya?.hasFixedSize()

        recyTerakhirHub?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyTerakhirHub?.isNestedScrollingEnabled = false
        recyTerakhirHub?.hasFixedSize()

        recyBarusanKom?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyBarusanKom?.isNestedScrollingEnabled = false
        recyBarusanKom?.hasFixedSize()

        StartSnapHelper().attachToRecyclerView(recyFavSaya)
        StartSnapHelper().attachToRecyclerView(recyTerakhirHub)
        StartSnapHelper().attachToRecyclerView(recyBarusanKom)

        itemPromo()
        itemLarisManis()
        itemPop()

        return rootView
    }

    private fun itemPromo() {
        val allItemPromoHemat = ArrayList<ProductModel>()

        allItemPromoHemat.add(ProductModel("https://bit.ly/2NT059I", "https://bit.ly/2NqCTQI", "John doe", "Boneka Baymax White 35cm", "Rp 57.000"))
        allItemPromoHemat.add(ProductModel("https://bit.ly/2POlYEn", "https://bit.ly/2NqCTQI", "John doe", "Xiaomi Mi A1 4/64GB - Android One", "Rp 2.420.000"))
        allItemPromoHemat.add(ProductModel("https://bit.ly/2Da0ccJ", "https://bit.ly/2NqCTQI", "John doe", "karpet bulu rasfur isi busa", "Rp 100.000"))
        allItemPromoHemat.add(ProductModel("https://bit.ly/2D9ybSP", "https://bit.ly/2NqCTQI", "John doe", "POWELL BLACK | FORIND x Navara", "Rp 260.000"))
        allItemPromoHemat.add(ProductModel("https://bit.ly/2xjpgsw", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "JBL Flip 3 Splashproof Portable Bluetooth", "Rp 1.055.000"))
        allItemPromoHemat.add(ProductModel("https://bit.ly/2NmKn7j", "https://bit.ly/2NqCTQI", "John doe", "COMPOUND RUBBING ALFAGLOSS", "Rp 21.000"))
        allItemPromoHemat.add(ProductModel("https://bit.ly/2xwcxlw", "https://bit.ly/2NqCTQI", "John doe", "Cracked Heel cream krim untuk kaki/tumit pecah-pecah", "Rp 27.000"))
        allItemPromoHemat.add(ProductModel("https://bit.ly/2QF9oZs", "https://bit.ly/2NqCTQI", "John doe", "Pulpen Gel Kokoro Zebra Colours", "Rp 3.800"))
        allItemPromoHemat.add(ProductModel("https://bit.ly/2xjpj7G", "https://bit.ly/2NqCTQI", "John doe", "Clean&Clear Cover & Correct BB cream 50ml", "Rp 130.000"))
        allItemPromoHemat.add(ProductModel("https://bit.ly/2NqD5iU", "https://bit.ly/2NqCTQI", "John doe", "Nanoblock Sagrada Familia", "Rp 224.925"))
        recyFavSaya?.adapter = ProductAdapter(allItemPromoHemat) { partItem: ProductModel -> partItemClicked(partItem) }
    }

    private fun itemLarisManis() {
        val allItemLarisManis = ArrayList<ProductModel>()

        allItemLarisManis.add(ProductModel("https://bit.ly/2zoOCH0", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Piyama Anak Perempuan Tsum Tsum Picnic", "Rp 83.000"))
        allItemLarisManis.add(ProductModel("https://bit.ly/2puKOhB", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "XPANDER SPOILER WARNA", "Rp 800.000"))
        allItemLarisManis.add(ProductModel("https://bit.ly/2xJZiOt", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Gamis syari balotelli polos / basic dress - Navy, L", "Rp 90.000"))
        allItemLarisManis.add(ProductModel("https://bit.ly/2NmKn7j", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "COMPOUND RUBBING ALFAGLOSS", "Rp 21.000"))
        allItemLarisManis.add(ProductModel("https://bit.ly/2QYrtC2", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "TOBOT ATHLON 2 MINI ROCKY", "Rp 41.000"))
        allItemLarisManis.add(ProductModel("https://bit.ly/2xMGAFG", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Guhdo Springbed Standard 2 In 1", "Rp 2.621.000"))
        allItemLarisManis.add(ProductModel("https://bit.ly/2Q0QA5X", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Alibi Paris Notte Bag - T4611R1", "Rp 83.100\n"))
        allItemLarisManis.add(ProductModel("https://bit.ly/2DpXgsE", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Ekstrak Albumin Ikan Gabus", "Rp 100.000"))
        allItemLarisManis.add(ProductModel("https://bit.ly/2O9xE7O", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Bubuk buah zuriat 100gram", "Rp 60.000"))
        allItemLarisManis.add(ProductModel("https://bit.ly/2xjpgsw", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "JBL Flip 3 Splashproof Portable Bluetooth", "Rp 1.055.000"))
        recyTerakhirHub?.adapter = ProductAdapter(allItemLarisManis) { partItem: ProductModel -> partItemClicked(partItem) }
    }

    private fun itemPop() {
        val allItemPop = ArrayList<ProductModel>()

        allItemPop.add(ProductModel("https://bit.ly/2xJZiOt", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Gamis syari balotelli polos / basic dress - Navy, L", "Rp 90.000"))
        allItemPop.add(ProductModel("https://bit.ly/2O9wTvu", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "The Beatles We Can Work It Out", "Rp 135.000"))
        allItemPop.add(ProductModel("https://bit.ly/2QYrtC2", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "TOBOT ATHLON 2 MINI ROCKY", "Rp 41.000"))
        allItemPop.add(ProductModel("https://bit.ly/2NqD5iU", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Nanoblock Sagrada Familia", "Rp 224.925"))
        allItemPop.add(ProductModel("https://bit.ly/2DpXgsE", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Ekstrak Albumin Ikan Gabus", "Rp 100.000"))
        allItemPop.add(ProductModel("https://bit.ly/2zppzDP", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Flash Drive Toshiba 16GB - Putih", "Rp 35.000"))
        allItemPop.add(ProductModel("https://bit.ly/2O6o4SX", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Handuk Merah Putih 30x70", "Rp 10.000"))
        allItemPop.add(ProductModel("https://bit.ly/2Q0QA5X", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Alibi Paris Notte Bag - T4611R1", "Rp 83.100"))
        allItemPop.add(ProductModel("https://bit.ly/2puuOvR", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Lampu Depan LED Tyto M2A Philips", "Rp 55.000"))
        allItemPop.add(ProductModel("https://bit.ly/2puKOhB", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "XPANDER SPOILER WARNA", "Rp 800.000"))
        recyBarusanKom?.adapter = ProductAdapter(allItemPop) { partItem: ProductModel -> partItemClicked(partItem) }
    }

    private fun partItemClicked(partItem: ProductModel) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("JUDUL", partItem.titleProduct)
        intent.putExtra("HARGA", partItem.priceProduct)
        intent.putExtra("COVER", partItem.imageCover)
        intent.putExtra("PROFILE", partItem.imageProfile)
        intent.putExtra("USERNAME", partItem.userName)
        startActivity(intent)
        (context as Activity).overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)
    }

}