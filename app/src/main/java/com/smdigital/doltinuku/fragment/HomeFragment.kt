package com.smdigital.doltinuku.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import com.smdigital.doltinuku.CustomItemClickListener
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.activity.CategoryActivity
import com.smdigital.doltinuku.activity.DetailActivity
import com.smdigital.doltinuku.activity.PromoActivity
import com.smdigital.doltinuku.adapter.BannerAdapter
import com.smdigital.doltinuku.adapter.CategoriesAdapter
import com.smdigital.doltinuku.adapter.ProductAdapter
import com.smdigital.doltinuku.helper.StartSnapHelper
import com.smdigital.doltinuku.model.BannerModel
import com.smdigital.doltinuku.model.CategoriesModel
import com.smdigital.doltinuku.model.ProductModel
import kotlinx.android.synthetic.main.content_fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private var imageAdapter: BannerAdapter? = null
    private val imageItemList = ArrayList<BannerModel>()
    private var pagerView: ViewPager? = null
    private var layIndicator: LinearLayout? = null
    private var recyCat: RecyclerView? = null
    private var recySedangRame: RecyclerView? = null
    private var recyBanyakSuka: RecyclerView? = null
    private var recyPop: RecyclerView? = null
    private var dataCount: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        (activity as AppCompatActivity).setSupportActionBar(view.toolbarMain)
        setDummy()
        imageAdapter = BannerAdapter(context, imageItemList, object : CustomItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                val intent = Intent(activity, PromoActivity::class.java)
                intent.putExtra("LINK", imageItemList[position].link)
                startActivity(intent)
                (context as Activity).overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)
            }
        })

        pagerView = view.viewPager
        layIndicator = view.indicator
        recyCat = view.recyclerCat
        recySedangRame = view.rvRame
        recyBanyakSuka = view.rvLaris
        recyPop = view.rvPopuler
        pagerView?.adapter = imageAdapter

        recyCat?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyCat?.isNestedScrollingEnabled = false
        recyCat?.hasFixedSize()

        recySedangRame?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recySedangRame?.isNestedScrollingEnabled = false
        recySedangRame?.hasFixedSize()

        recyBanyakSuka?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyBanyakSuka?.isNestedScrollingEnabled = false
        recyBanyakSuka?.hasFixedSize()

        recyPop?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyPop?.isNestedScrollingEnabled = false
        recyPop?.hasFixedSize()

        StartSnapHelper().attachToRecyclerView(recyPop)
        StartSnapHelper().attachToRecyclerView(recyCat)
        StartSnapHelper().attachToRecyclerView(recySedangRame)
        StartSnapHelper().attachToRecyclerView(recyBanyakSuka)

        autoSwipeBanner()
        setIndicator()
        allCategory()
        itemSedangRame()
        itemLarisManis()
        itemPop()
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_search -> showDialog()
            else -> {
            }
        }
        return true
    }

    private fun allCategory() {
        val itemCard = ArrayList<CategoriesModel>()

        itemCard.add(CategoriesModel(R.drawable.fashion, "Fashion"))
        itemCard.add(CategoriesModel(R.drawable.electronic, "Elektronik"))
        itemCard.add(CategoriesModel(R.drawable.automotive, "Otomotif"))
        itemCard.add(CategoriesModel(R.drawable.beauty, "Kecantikan & Kesehatan"))
        itemCard.add(CategoriesModel(R.drawable.accesories, "Aksesoris"))
        itemCard.add(CategoriesModel(R.drawable.house, "Peralatan rumah"))

        recyCat?.adapter = CategoriesAdapter(itemCard) { partItemC: CategoriesModel -> partCategoryClicked(partItemC) }
    }

    private fun setDummy() {
        imageItemList.add(BannerModel(0, "https://bit.ly/2MLykvh", "https://bit.ly/2MLykvh"))
        imageItemList.add(BannerModel(1, "https://bit.ly/2Nmi7lb", "https://bit.ly/2Nmi7lb"))
        imageItemList.add(BannerModel(2, "https://bit.ly/2xmz7xM", "https://bit.ly/2xmz7xM"))
        imageItemList.add(BannerModel(3, "https://bit.ly/2NSGG8V", "https://bit.ly/2NSGG8V"))
        imageItemList.add(BannerModel(4, "https://bit.ly/2pmcOUk", "https://bit.ly/2pmcOUk"))
    }

    private fun itemSedangRame() {
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
        recySedangRame?.adapter = ProductAdapter(allItemPromoHemat) { partItem: ProductModel -> partItemClicked(partItem) }
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
        recyBanyakSuka?.adapter = ProductAdapter(allItemLarisManis) { partItem: ProductModel -> partItemClicked(partItem) }
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
        recyPop?.adapter = ProductAdapter(allItemPop) { partItem: ProductModel -> partItemClicked(partItem) }
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

    private fun partCategoryClicked(partItemC: CategoriesModel) {
        val intent = Intent(activity, CategoryActivity::class.java)
        intent.putExtra("CATEGORY", partItemC.title)
        startActivity(intent)
        (context as Activity).overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)
    }

    private fun setIndicator() {
        dataCount = imageAdapter?.count!!
        val dot: Array<ImageView?>?
        dot = arrayOfNulls(dataCount)

        for (i in 0 until dataCount) {
            dot[i] = ImageView(activity)
            dot[i]?.setImageResource(R.drawable.indicator_unselect)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 0, 0)
            layIndicator?.addView(dot[i], params)
        }
        dot[0]?.setImageResource(R.drawable.indicator_select)
        pagerView?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                for (i in 0 until dataCount) {
                    dot[i]?.setImageResource(R.drawable.indicator_unselect)
                }
                dot[position]?.setImageResource(R.drawable.indicator_select)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun autoSwipeBanner() {
        val handler = Handler()
        val update = Runnable {
            var currentPage = pagerView?.currentItem
            if (currentPage == imageItemList.size - 1) {
                currentPage = -1
            }
            if (currentPage != null) {
                pagerView?.setCurrentItem(currentPage + 1, true)
            }
        }

        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {

            override fun run() {
                handler.post(update)
            }
        }, 5000, 10000)
    }

    private fun showDialog() {}
}