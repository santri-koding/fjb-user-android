package com.smdigital.doltinuku.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.adapter.AllCategoriesAdapter
import com.smdigital.doltinuku.model.ProductModel
import kotlinx.android.synthetic.main.activity_store.*

class StoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_outline_close)
        supportActionBar?.title = null

        rvEtalaseStore.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        rvEtalaseStore.isNestedScrollingEnabled = false
        rvEtalaseStore.hasFixedSize()

        itemPromo()
        setBar()
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
        rvEtalaseStore.adapter = AllCategoriesAdapter(allItemPromoHemat) { partItem: ProductModel -> partItemClicked(partItem) }
    }

    private fun partItemClicked(partItem: ProductModel) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("JUDUL", partItem.titleProduct)
        intent.putExtra("HARGA", partItem.priceProduct)
        intent.putExtra("COVER", partItem.imageCover)
        intent.putExtra("PROFILE", partItem.imageProfile)
        intent.putExtra("USERNAME", partItem.userName)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_forward, R.anim.slide_out_right)
    }

    private fun setBar() {
        appBarLay.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }

                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.title = "John doe"
                    isShow = true
                } else if (isShow) {
                    collapsingToolbar.title = null
                    isShow = false
                }
            }
        })
    }
}
