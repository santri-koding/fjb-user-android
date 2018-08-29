package com.smdigital.doltinuku.fragment

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
import com.smdigital.doltinuku.StartSnapHelper
import com.smdigital.doltinuku.adapter.BannerAdapter
import com.smdigital.doltinuku.adapter.CategoriesAdapter
import com.smdigital.doltinuku.adapter.ProductAdapter
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
    private var recyPm: RecyclerView? = null
    private var recyLm: RecyclerView? = null
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
                //startActivity(Intent(getApplicationContext(), SlideActivity::class.java))
            }
        })

        pagerView = view.viewPager
        layIndicator = view.indicator
        recyCat = view.recyclerCat
        recyPm = view.rvPromoHemat
        recyLm = view.rvLaris
        recyPop = view.rvPopuler
        pagerView?.adapter = imageAdapter

        recyCat?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyCat?.isNestedScrollingEnabled = false
        recyCat?.setHasFixedSize(true)

        recyPm?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyPm?.isNestedScrollingEnabled = false
        recyPm?.setHasFixedSize(true)

        recyLm?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyLm?.isNestedScrollingEnabled = false
        recyLm?.setHasFixedSize(true)

        recyPop?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyPop?.isNestedScrollingEnabled = false
        recyPop?.setHasFixedSize(true)

        StartSnapHelper().attachToRecyclerView(recyPop)
        StartSnapHelper().attachToRecyclerView(recyCat)
        StartSnapHelper().attachToRecyclerView(recyPm)
        StartSnapHelper().attachToRecyclerView(recyLm)

        autoSwipeBanner()
        setIndicator()
        allCategory()
        itemPromo()
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

        recyCat?.adapter = CategoriesAdapter(itemCard)
    }

    private fun setDummy() {
        imageItemList.add(BannerModel(0, R.drawable.promotion))
        imageItemList.add(BannerModel(1, R.drawable.smartphone))
        imageItemList.add(BannerModel(2, R.drawable.shoe))
        imageItemList.add(BannerModel(3, R.drawable.tv))
        imageItemList.add(BannerModel(4, R.drawable.cosmetic))
    }

    private fun itemPromo() {
        val allItemPromoHemat = ArrayList<ProductModel>()

        allItemPromoHemat.add(ProductModel(R.drawable.a1, "Jam tangan", "Rp2.3Juta"))
        allItemPromoHemat.add(ProductModel(R.drawable.e1, "Jam tangan", "Rp2.3Juta"))
        allItemPromoHemat.add(ProductModel(R.drawable.r2, "Jam tangan", "Rp2.3Juta"))
        allItemPromoHemat.add(ProductModel(R.drawable.f5, "Jam tangan", "Rp2.3Juta"))
        allItemPromoHemat.add(ProductModel(R.drawable.e4, "Jam tangan", "Rp2.3Juta"))
        allItemPromoHemat.add(ProductModel(R.drawable.o4, "Jam tangan", "Rp2.3Juta"))
        allItemPromoHemat.add(ProductModel(R.drawable.c5, "Jam tangan", "Rp2.3Juta"))
        allItemPromoHemat.add(ProductModel(R.drawable.a2, "Jam tangan", "Rp2.3Juta"))
        allItemPromoHemat.add(ProductModel(R.drawable.c3, "Jam tangan", "Rp2.3Juta"))
        allItemPromoHemat.add(ProductModel(R.drawable.a4, "Jam tangan", "Rp2.3Juta"))
        recyPm?.adapter = ProductAdapter(allItemPromoHemat)
    }

    private fun itemLarisManis() {
        val allItemLarisManis = ArrayList<ProductModel>()

        allItemLarisManis.add(ProductModel(R.drawable.f4, "Jam tangan", "Rp2.3Juta"))
        allItemLarisManis.add(ProductModel(R.drawable.e2, "Jam tangan", "Rp2.3Juta"))
        allItemLarisManis.add(ProductModel(R.drawable.r3, "Jam tangan", "Rp2.3Juta"))
        allItemLarisManis.add(ProductModel(R.drawable.o2, "Jam tangan", "Rp2.3Juta"))
        allItemLarisManis.add(ProductModel(R.drawable.c4, "Jam tangan", "Rp2.3Juta"))
        allItemLarisManis.add(ProductModel(R.drawable.r1, "Jam tangan", "Rp2.3Juta"))
        allItemLarisManis.add(ProductModel(R.drawable.f1, "Jam tangan", "Rp2.3Juta"))
        allItemLarisManis.add(ProductModel(R.drawable.e1, "Jam tangan", "Rp2.3Juta"))
        allItemLarisManis.add(ProductModel(R.drawable.c5, "Jam tangan", "Rp2.3Juta"))
        allItemLarisManis.add(ProductModel(R.drawable.e3, "Jam tangan", "Rp2.3Juta"))
        recyLm?.adapter = ProductAdapter(allItemLarisManis)
    }

    private fun itemPop() {
        val allItemPop = ArrayList<ProductModel>()

        allItemPop.add(ProductModel(R.drawable.f3, "Jam tangan", "Rp2.3Juta"))
        allItemPop.add(ProductModel(R.drawable.f2, "Jam tangan", "Rp2.3Juta"))
        allItemPop.add(ProductModel(R.drawable.a3, "Jam tangan", "Rp2.3Juta"))
        allItemPop.add(ProductModel(R.drawable.a5, "Jam tangan", "Rp2.3Juta"))
        allItemPop.add(ProductModel(R.drawable.c2, "Jam tangan", "Rp2.3Juta"))
        allItemPop.add(ProductModel(R.drawable.e3, "Jam tangan", "Rp2.3Juta"))
        allItemPop.add(ProductModel(R.drawable.r5, "Jam tangan", "Rp2.3Juta"))
        allItemPop.add(ProductModel(R.drawable.f1, "Jam tangan", "Rp2.3Juta"))
        allItemPop.add(ProductModel(R.drawable.o3, "Jam tangan", "Rp2.3Juta"))
        allItemPop.add(ProductModel(R.drawable.o1, "Jam tangan", "Rp2.3Juta"))
        recyPop?.adapter = ProductAdapter(allItemPop)
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
        val Update = Runnable {
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
                handler.post(Update)
            }
        }, 5000, 10000)
    }

    private fun showDialog() {}
}