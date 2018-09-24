package com.smdigital.doltinuku.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.activity.DetailActivity
import com.smdigital.doltinuku.adapter.AllCategoriesAdapter
import com.smdigital.doltinuku.model.ProductModel
import kotlinx.android.synthetic.main.fragment_menu_sell.view.*

class MenuSellFragment : Fragment() {

    private var recySell: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_menu_sell, container, false)
        // Inflate the layout for this fragment
        recySell = rootView.recyclerEtalase

        recySell?.layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        recySell?.hasFixedSize()

        itemSell()
        return rootView
    }

    private fun itemSell() {
        val allItemFeed = ArrayList<ProductModel>()
        allItemFeed.add(ProductModel("https://bit.ly/2O9wTvu", "https://images.pexels.com/users/avatars/312601/dam-t-ng-quan-741.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "The Beatles We Can Work It Out", "Rp 135.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2NqD5iU", "https://images.pexels.com/users/avatars/54196/madison-inouye-175.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Nanoblock Sagrada Familia", "Rp 224.925"))
        allItemFeed.add(ProductModel("https://bit.ly/2O9xE7O", "https://images.pexels.com/users/avatars/358536/d-van-trung-422.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Bubuk buah zuriat 100gram", "Rp 60.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2DoZZCO", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Happy Banana Monkey Couple Figurine ", "Rp 89.900"))
        allItemFeed.add(ProductModel("https://bit.ly/2Q0QA5X", "https://images.pexels.com/users/avatars/312601/dam-t-ng-quan-741.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Alibi Paris Notte Bag - T4611R1", "Rp 83.100"))
        allItemFeed.add(ProductModel("https://bit.ly/2QF9oZs", "https://images.pexels.com/users/avatars/54196/madison-inouye-175.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Pulpen Gel Kokoro Zebra Colours", "Rp 3.800"))
        allItemFeed.add(ProductModel("https://bit.ly/2QYrtC2", "https://images.pexels.com/users/avatars/54196/madison-inouye-175.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "TOBOT ATHLON 2 MINI ROCKY", "Rp 41.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2DnlsMr", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Handuk Merah Putih 30x70", "Rp 10.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2OGql4i", "https://images.pexels.com/users/avatars/358536/d-van-trung-422.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Foligain 5% Minoxidil Extra Strength for Men", "Rp 450.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2xJZiOt", "https://images.pexels.com/users/avatars/312601/dam-t-ng-quan-741.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Gamis syari balotelli polos / basic dress - Navy, L", "Rp 90.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2MVhREP", "https://images.pexels.com/users/avatars/571159/fox-254.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "HELM RETRO BOGO KULIT CLASIC COKLAT KREM", "Rp 135.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2NT059I", "https://images.pexels.com/users/avatars/54196/madison-inouye-175.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Boneka Baymax White 35cm", "Rp 57.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2POlYEn", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Xiaomi Mi A1 4/64GB - Android One", "Rp 2.420.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2pwqY5K", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "ASUS E203NAH,N3350, 2GB,500GB 11,3\"", "Rp 3.300.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2xwcxlw", "https://images.pexels.com/users/avatars/358536/d-van-trung-422.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Cracked Heel cream krim untuk kaki/tumit pecah-pecah", "Rp 27.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2puKOhB", "https://images.pexels.com/users/avatars/571159/fox-254.jpeg?w=60&h=60&fit=crop&crop=faces3", "John doe", "XPANDER SPOILER WARNA", "Rp 800.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2xjpj7G", "https://images.pexels.com/users/avatars/358536/d-van-trung-422.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Clean&Clear Cover & Correct BB cream 50ml", "Rp 130.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2zppzDP", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Flash Drive Toshiba 16GB - Putih", "Rp 35.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2zoOCH0", "https://images.pexels.com/users/avatars/312601/dam-t-ng-quan-741.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Piyama Anak Perempuan Tsum Tsum Picnic", "Rp 83.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2puuOvR", "https://images.pexels.com/users/avatars/571159/fox-254.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Lampu Depan LED Tyto M2A Philips", "Rp 55.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2pxChKQ", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Pisau Set Vicenza Original Warna Hitam", "Rp 122.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2DqqQyi", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "JBL Flip 3 Splashproof Portable Bluetooth ", "Rp 1.055.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2D9ybSP", "https://images.pexels.com/users/avatars/312601/dam-t-ng-quan-741.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "POWELL BLACK | FORIND x Navara", "Rp 260.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2NmKn7j", "https://images.pexels.com/users/avatars/571159/fox-254.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "COMPOUND RUBBING ALFAGLOSS", "Rp 21.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2Da0ccJ", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "karpet bulu rasfur isi busa", "Rp 100.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2pwraC0", "https://images.pexels.com/users/avatars/54196/madison-inouye-175.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Lego Keychain - Sponge Bob", "Rp 100.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2O6o4SX", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Handuk Merah Putih 30x70", "Rp 10.000"))
        allItemFeed.add(ProductModel("https://bit.ly/2DpXgsE", "https://images.pexels.com/users/avatars/358536/d-van-trung-422.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Ekstrak Albumin Ikan Gabus", "Rp 100.000"))
        recySell?.adapter = AllCategoriesAdapter(allItemFeed) { partItem: ProductModel -> partItemClicked(partItem) }
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
