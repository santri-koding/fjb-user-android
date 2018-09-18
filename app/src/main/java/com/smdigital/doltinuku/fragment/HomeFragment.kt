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
import com.smdigital.doltinuku.StartSnapHelper
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
        imageItemList.add(BannerModel(0, "https://www.babypeak.com/wp-content/uploads/2018/07/%E0%B9%82%E0%B8%9B%E0%B8%A3%E0%B9%82%E0%B8%A1%E0%B8%8A%E0%B8%B1%E0%B9%88%E0%B8%99%E0%B8%A3%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%97%E0%B9%89%E0%B8%B2%E0%B9%80%E0%B8%94%E0%B9%87%E0%B8%81%E0%B8%A5%E0%B8%94%E0%B8%A3%E0%B8%B2%E0%B8%84%E0%B8%B2%E0%B8%9E%E0%B8%B4%E0%B9%80%E0%B8%A8%E0%B8%A9-600x206.jpg", "https://www.babypeak.com/wp-content/uploads/2018/07/%E0%B9%82%E0%B8%9B%E0%B8%A3%E0%B9%82%E0%B8%A1%E0%B8%8A%E0%B8%B1%E0%B9%88%E0%B8%99%E0%B8%A3%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%97%E0%B9%89%E0%B8%B2%E0%B9%80%E0%B8%94%E0%B9%87%E0%B8%81%E0%B8%A5%E0%B8%94%E0%B8%A3%E0%B8%B2%E0%B8%84%E0%B8%B2%E0%B8%9E%E0%B8%B4%E0%B9%80%E0%B8%A8%E0%B8%A9-600x206.jpg"))
        imageItemList.add(BannerModel(1, "data:image/jpeg;base64,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", "data:image/jpeg;base64,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"))
        imageItemList.add(BannerModel(2, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwC3-8VuRd__hq7K2snPzs9bdri5LGy9HSDAjlGkSY0wXvTVmK", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwC3-8VuRd__hq7K2snPzs9bdri5LGy9HSDAjlGkSY0wXvTVmK"))
        imageItemList.add(BannerModel(3, "https://www.appliancesdirect.co.uk/files/images/apd/lpbanner-SamsungWorldCup.jpg", "https://www.appliancesdirect.co.uk/files/images/apd/lpbanner-SamsungWorldCup.jpg"))
        imageItemList.add(BannerModel(4, "https://www.tangs.com/SharedImages/BrandImages/CHANEL/mobileimage_1.JPG?timestamp=636718879902917576", "https://www.tangs.com/SharedImages/BrandImages/CHANEL/mobileimage_1.JPG?timestamp=636718879902917576"))
    }

    private fun itemPromo() {
        val allItemPromoHemat = ArrayList<ProductModel>()

        allItemPromoHemat.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2015/4/6/297009/297009_ff7f749c-dc75-11e4-9017-fec24908a8c2.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Boneka Baymax White 35cm", "Rp 57.000"))
        allItemPromoHemat.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/10/21/217841769/217841769_78e6648a-60ec-4a90-8308-aa1c039ea5e3_683_600.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Xiaomi Mi A1 4/64GB - Android One", "Rp 2.420.000"))
        allItemPromoHemat.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/5/17/96230209/96230209_2e7f2fee-c2f3-4799-a769-94e43f8e376b_640_640.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "karpet bulu rasfur isi busa", "Rp 100.000"))
        allItemPromoHemat.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/5/8/7933738/7933738_e59f3f9c-41aa-4d25-9350-107edee972ed_1498_1498.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "POWELL BLACK | FORIND x Navara", "Rp 260.000"))
        allItemPromoHemat.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/11/5/9583069/9583069_1bd9432d-c3c0-4523-82cf-0e73bbc3e459_700_700.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "JBL Flip 3 Splashproof Portable Bluetooth", "Rp 1.055.000"))
        allItemPromoHemat.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/3/18/14934123/14934123_33b0351c-c611-418f-9b7c-9850edeadc62_300_315.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "COMPOUND RUBBING ALFAGLOSS", "Rp 21.000"))
        allItemPromoHemat.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/4/25/31661069/31661069_faf4b477-d2ae-4b4c-849f-4cfd5b54914e_870_852.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Cracked Heel cream krim untuk kaki/tumit pecah-pecah", "Rp 27.000"))
        allItemPromoHemat.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/11/22/224247891/224247891_ceefffc6-3f99-4ac9-baef-37bce42e91b0_2048_0.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Pulpen Gel Kokoro Zebra Colours", "Rp 3.800"))
        allItemPromoHemat.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/8/21/11412309/11412309_4b438402-e730-4560-bc45-97c074c6955f_1000_1431.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Clean&Clear Cover & Correct BB cream 50ml", "Rp 130.000"))
        allItemPromoHemat.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/4/11/523375/523375_b094050a-eb39-423b-9e72-a29e986107e2.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Nanoblock Sagrada Familia", "Rp 224.925"))
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