package com.smdigital.doltinuku.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.smdigital.doltinuku.R
import kotlinx.android.synthetic.main.activity_promo.*

class PromoActivity : AppCompatActivity() {

    private val url: String = "https://www.suaramerdeka.com/"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_outline_close)

        wvPromo.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pbPromo.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pbPromo.visibility = View.GONE
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                pbPromo.visibility = View.GONE
            }

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                wvPromo.loadUrl(url)
                return true
            }
        }


        wvPromo.settings.loadsImagesAutomatically = true
        wvPromo.settings.javaScriptEnabled = true
        wvPromo.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY;
        wvPromo.settings.useWideViewPort = true
        wvPromo.settings.loadWithOverviewMode = true
        wvPromo.isHorizontalScrollBarEnabled = false
        wvPromo.loadUrl(url)
    }

/*    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_forward, R.anim.slide_out_right)
    }*/
}
