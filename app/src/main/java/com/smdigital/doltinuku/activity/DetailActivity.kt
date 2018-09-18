package com.smdigital.doltinuku.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.smdigital.doltinuku.CustomItemClickListener
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.adapter.BannerAdapter
import com.smdigital.doltinuku.model.BannerModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import com.google.android.gms.maps.GoogleMapOptions
import com.smdigital.doltinuku.GlideApp


class DetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private var dataCount: Int = 0
    private var imageAdapter: BannerAdapter? = null
    private val imageItemList = ArrayList<BannerModel>()
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        val ss = intent.getStringExtra("JUDUL")
        val urlCover = intent.getStringExtra("COVER")
        val harga = intent.getStringExtra("HARGA")
        val poto = intent.getStringExtra("PROFILE")
        val user = intent.getStringExtra("USERNAME")

        setDummy(urlCover)
        setBar()

        imageAdapter = BannerAdapter(this, imageItemList, object : CustomItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                //startActivity(Intent(this@DetailActivity, FullscreenActivity::class.java))
                val intent = Intent(this@DetailActivity, FullscreenActivity::class.java)
                intent.putExtra("FULLCOVER", urlCover)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)
            }
        })

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.mapSeller) as SupportMapFragment
        mapFragment.getMapAsync(this)

        pager.adapter = imageAdapter
        setIndicator()

        tvTitleDetail.text = ss
        tvPriceDetail.text = harga
        tvUsername.text = user
        GlideApp.with(this).load(poto).into(civProfile)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
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

    private fun setBar(){
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener{
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }

                if (scrollRange + verticalOffset == 0) {
                    toolbar_layout.title = null
                    isShow = true
                } else if (isShow) {
                    toolbar_layout.title = null
                    isShow = false
                }
            }
        })
    }

    private fun setDummy(url: String) {
        imageItemList.add(BannerModel(0, url, url))
    }

    private fun setIndicator() {
        dataCount = imageAdapter?.count!!
        val dot: Array<ImageView?>?
        dot = arrayOfNulls(dataCount)

        for (i in 0 until dataCount) {
            dot[i] = ImageView(this)
            dot[i]?.setImageResource(R.drawable.indicator_unselect)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 0, 0)
            indicatorCover.addView(dot[i], params)
        }
        dot[0]?.setImageResource(R.drawable.indicator_select)
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-6.9861902, 110.4131805)
        mMap.addMarker(MarkerOptions().position(sydney).title("Menara Suara Merdeka"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}