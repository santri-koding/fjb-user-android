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
import com.smdigital.doltinuku.helper.StartSnapHelper
import com.smdigital.doltinuku.activity.CategoryActivity
import com.smdigital.doltinuku.activity.DetailActivity
import com.smdigital.doltinuku.activity.PromoActivity
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
                val intent = Intent(activity, PromoActivity::class.java)
                intent.putExtra("LINK", imageItemList[position].link)
                startActivity(intent)
                (context as Activity).overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)
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
        recyCat?.hasFixedSize()

        recyPm?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyPm?.isNestedScrollingEnabled = false
        recyPm?.hasFixedSize()

        recyLm?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyLm?.isNestedScrollingEnabled = false
        recyLm?.hasFixedSize()

        recyPop?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyPop?.isNestedScrollingEnabled = false
        recyPop?.hasFixedSize()

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

        recyCat?.adapter = CategoriesAdapter(itemCard) { partItemC: CategoriesModel -> partCategoryClicked(partItemC) }
    }

    private fun setDummy() {
        imageItemList.add(BannerModel(0, "https://bit.ly/2MLykvh", "https://bit.ly/2MLykvh"))
        imageItemList.add(BannerModel(1, "https://bit.ly/2Nmi7lb", "https://bit.ly/2Nmi7lb"))
        imageItemList.add(BannerModel(2, "https://bit.ly/2xmz7xM", "https://bit.ly/2xmz7xM"))
        imageItemList.add(BannerModel(3, "https://bit.ly/2NSGG8V", "https://bit.ly/2NSGG8V"))
        imageItemList.add(BannerModel(4, "https://bit.ly/2pmcOUk", "https://bit.ly/2pmcOUk"))
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
        recyPm?.adapter = ProductAdapter(allItemPromoHemat) { partItem: ProductModel -> partItemClicked(partItem) }
    }

    private fun itemLarisManis() {
        val allItemLarisManis = ArrayList<ProductModel>()

        allItemLarisManis.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/9/28/21153453/21153453_4596e241-c4ad-4633-ae42-c3957ea14464_600_500.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Piyama Anak Perempuan Tsum Tsum Picnic", "Rp 83.000"))
        allItemLarisManis.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/4/26/3263085/3263085_0300ba27-3ee5-48ad-a10b-36d9ee8f8387_1024_1024.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "XPANDER SPOILER WARNA", "Rp 800.000"))
        allItemLarisManis.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/4/15/2034422/2034422_bc2bcc82-d096-4b48-8100-df7680d464e8.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Gamis syari balotelli polos / basic dress - Navy, L", "Rp 90.000"))
        allItemLarisManis.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/3/18/14934123/14934123_33b0351c-c611-418f-9b7c-9850edeadc62_300_315.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "COMPOUND RUBBING ALFAGLOSS", "Rp 21.000"))
        allItemLarisManis.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/5/15/7566550/7566550_62a1f6f9-8775-45d2-804f-f9b68b8d16a5_960_1280.jpeg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "TOBOT ATHLON 2 MINI ROCKY", "Rp 41.000"))
        allItemLarisManis.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/6/11/11105445/11105445_cc511512-3491-4744-af11-e58a18354582_700_477.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Guhdo Springbed Standard 2 In 1", "Rp 2.621.000"))
        allItemLarisManis.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/7/25/20248783/20248783_108a8f9f-6993-4eab-be20-4c2757579b89_1000_1000.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Alibi Paris Notte Bag - T4611R1", "Rp 83.100\n"))
        allItemLarisManis.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/1/10/7546897/7546897_24bbb41c-aa0e-4bea-9b95-85ed7cd19a8f_2048_0.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Ekstrak Albumin Ikan Gabus", "Rp 100.000"))
        allItemLarisManis.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/5/24/162320663/162320663_984b9322-afa2-4ab9-acd9-949442d01343_955_1011.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Bubuk buah zuriat 100gram", "Rp 60.000"))
        allItemLarisManis.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/11/5/9583069/9583069_1bd9432d-c3c0-4523-82cf-0e73bbc3e459_700_700.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "JBL Flip 3 Splashproof Portable Bluetooth", "Rp 1.055.000"))
        recyLm?.adapter = ProductAdapter(allItemLarisManis) { partItem: ProductModel -> partItemClicked(partItem) }
    }

    private fun itemPop() {
        val allItemPop = ArrayList<ProductModel>()

        allItemPop.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/4/15/2034422/2034422_bc2bcc82-d096-4b48-8100-df7680d464e8.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Gamis syari balotelli polos / basic dress - Navy, L", "Rp 90.000"))
        allItemPop.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/7/23/309438718/309438718_902599f6-b1fe-4bf9-b24a-0f5b7d50835a_2048_2730.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "The Beatles We Can Work It Out", "Rp 135.000"))
        allItemPop.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/5/15/7566550/7566550_62a1f6f9-8775-45d2-804f-f9b68b8d16a5_960_1280.jpeg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "TOBOT ATHLON 2 MINI ROCKY", "Rp 41.000"))
        allItemPop.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/4/11/523375/523375_b094050a-eb39-423b-9e72-a29e986107e2.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Nanoblock Sagrada Familia", "Rp 224.925"))
        allItemPop.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/1/10/7546897/7546897_24bbb41c-aa0e-4bea-9b95-85ed7cd19a8f_2048_0.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Ekstrak Albumin Ikan Gabus", "Rp 100.000"))
        allItemPop.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/4/14/15734237/15734237_5a0c256a-9bdd-44e3-ba50-ec4a59df2f56_700_622.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Flash Drive Toshiba 16GB - Putih", "Rp 35.000"))
        allItemPop.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/7/11/1349173/1349173_ab3062ed-ad1a-44a1-980b-0e1652cf9e5b_850_850.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Handuk Merah Putih 30x70", "Rp 10.000"))
        allItemPop.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/7/25/20248783/20248783_108a8f9f-6993-4eab-be20-4c2757579b89_1000_1000.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Alibi Paris Notte Bag - T4611R1", "Rp 83.100"))
        allItemPop.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/9/20/20484439/20484439_58f73ff3-0e56-43b3-a2a0-10088b594937_720_720.png", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Lampu Depan LED Tyto M2A Philips", "Rp 55.000"))
        allItemPop.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/4/26/3263085/3263085_0300ba27-3ee5-48ad-a10b-36d9ee8f8387_1024_1024.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "XPANDER SPOILER WARNA", "Rp 800.000"))
//        recyPop?.adapter = ProductAdapter(allItemPop){ partItem: ProductModel -> partItemClicked(partItem)}
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