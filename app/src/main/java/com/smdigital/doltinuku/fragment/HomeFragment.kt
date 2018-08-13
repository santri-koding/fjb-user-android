package com.smdigital.doltinuku.fragment

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import com.smdigital.doltinuku.BannerAdapter
import com.smdigital.doltinuku.BannerModel
import com.smdigital.doltinuku.CustomItemClickListener
import com.smdigital.doltinuku.R
import kotlinx.android.synthetic.main.content_fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*


class HomeFragment : Fragment() {

    private var page = 0
    private var imageAdapter: BannerAdapter? = null
    private val imageItemList = ArrayList<BannerModel>()
    private var pagerView: ViewPager? = null
    private var layIndicator: LinearLayout? = null
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
        pagerView?.adapter = imageAdapter

        autoSwipeBanner()
        setIndicator()
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

    private fun setDummy() {
        imageItemList.add(BannerModel(0, R.drawable.promotion))
        imageItemList.add(BannerModel(1, R.drawable.smartphone))
        imageItemList.add(BannerModel(2, R.drawable.shoe))
        imageItemList.add(BannerModel(3, R.drawable.tv))
        imageItemList.add(BannerModel(4, R.drawable.cosmetic))
    }

    private fun setIndicator() {
        dataCount = imageAdapter?.count!!
        val dot: Array<ImageView?>?
        dot = arrayOfNulls(dataCount)

        for (i in 0 until dataCount) {
            dot[i] = ImageView(activity)
            dot[i]?.setImageResource(R.drawable.indicator_unselect)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 8, 0)
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

    fun autoSwipeBanner() {
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