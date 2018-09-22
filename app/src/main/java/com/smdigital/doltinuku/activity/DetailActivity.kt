package com.smdigital.doltinuku.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
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
import com.smdigital.doltinuku.GlideApp
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.adapter.BannerAdapter
import com.smdigital.doltinuku.model.BannerModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_detail.*


class DetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private var dataCount: Int = 0
    private var imageAdapter: BannerAdapter? = null
    private val imageItemList = ArrayList<BannerModel>()
    private lateinit var mMap: GoogleMap
    private val TAG = DetailActivity::class.java.simpleName
    private val MY_PERMISSIONS_REQUEST_CALL_PHONE = 1
    private var mTelephonyManager: TelephonyManager? = null
    private var mListener: MyPhoneCallListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        mTelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        // Check to see if Telephony is enabled.
        if (isTelephonyEnabled()) {
            Log.d(TAG, getString(R.string.telephony_enabled))
            // Check for phone permission.
            checkForPhonePermission()
            // Register the PhoneStateListener to monitor phone activity.
            mListener = MyPhoneCallListener()
            mTelephonyManager!!.listen(mListener, PhoneStateListener.LISTEN_CALL_STATE)
        } else {
            Log.d(TAG, getString(R.string.telephony_not_enabled))
        }

        val judul = intent.getStringExtra("JUDUL")
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

        tvTitleDetail.text = judul
        tvPriceDetail.text = harga
        tvUsername.text = user
        GlideApp.with(this).load(poto).into(civProfile)

        ibCall.setOnClickListener {
            callNumber("082224192959")
        }

        ibSMS.setOnClickListener {
            sendMessage("082224192959", judul)
        }
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

    private fun setBar() {
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
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

    private fun isTelephonyEnabled(): Boolean {
        if (mTelephonyManager != null) {
            if (mTelephonyManager!!.simState == TelephonyManager.SIM_STATE_READY) {
                return true
            }
        }
        return false
    }

    private fun checkForPhonePermission() {
        if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, getString(R.string.permission_not_granted))
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    MY_PERMISSIONS_REQUEST_CALL_PHONE)
        } else {
            // Permission already granted. Enable the call button.
            //enableCallButton()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        // Check if permission is granted or not for the request.
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CALL_PHONE -> {
                if (permissions[0].equals(Manifest.permission.CALL_PHONE, ignoreCase = true) && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted. Enable call button.
                    //enableCallButton()
                } else {
                    // Permission denied.
                    Log.d(TAG, getString(R.string.failure_permission))
                }
            }
        }
    }

    private fun sendMessage(noPhone: String, title: String){
        val smsNumber = String.format("smsto: %s", noPhone)
        val smsIntent = Intent(Intent.ACTION_SENDTO)
        smsIntent.data = Uri.parse(smsNumber)
        smsIntent.putExtra("sms_body", "$title\n#FJB_SM")
        if (smsIntent.resolveActivity(packageManager) != null) {
            startActivity(smsIntent)
        } else {
            Log.e(TAG, "Can't resolve app for ACTION_SENDTO Intent.")
        }
    }

    private fun callNumber(noPhone: String) {
        // Use format with "tel:" and phone number to create phoneNumber.
        val phoneNumber = String.format("tel: %s", noPhone)
        // Log the concatenated phone number for dialing.
        Log.d(TAG, getString(R.string.dial_number) + phoneNumber)
        // Create the intent.
        val callIntent = Intent(Intent.ACTION_CALL)
        // Set the data for the intent as the phone number.
        callIntent.data = Uri.parse(phoneNumber)
        // If package resolves to an app, check for phone permission,
        // and send intent.
        if (callIntent.resolveActivity(packageManager) != null) {
            checkForPhonePermission()
            startActivity(callIntent)
        } else {
            Log.e(TAG, "Can't resolve app for ACTION_CALL Intent.")
        }
    }

    private inner class MyPhoneCallListener : PhoneStateListener() {
        private var returningFromOffHook = false

        @SuppressLint("ObsoleteSdkInt")
        override fun onCallStateChanged(state: Int, incomingNumber: String) {
            // Define a string for the message to use in a toast.
            var message = getString(R.string.phone_status)
            when (state) {
                TelephonyManager.CALL_STATE_RINGING -> {
                    // Incoming call is ringing (not used for outgoing call).
                    message = message +
                            getString(R.string.ringing) + incomingNumber
                    Log.i(TAG, message)
                }
                TelephonyManager.CALL_STATE_OFFHOOK -> {
                    // Phone call is active -- off the hook.
                    message += getString(R.string.offhook)
                    Log.i(TAG, message)
                    returningFromOffHook = true
                }
                TelephonyManager.CALL_STATE_IDLE -> {
                    // Phone is idle before and after phone call.
                    // If running on version older than 19 (KitKat),
                    // restart activity when phone call ends.
                    message += getString(R.string.idle)
                    Log.i(TAG, message)
                    if (returningFromOffHook) {
                        // No need to do anything if >= version KitKat.
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                            Log.i(TAG, getString(R.string.restarting_app))
                            // Restart the app.
                            val intent = packageManager
                                    .getLaunchIntentForPackage(packageName)
                            intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                        }
                    }
                }
                else -> {
                    message += "Phone off"
                    Log.i(TAG, message)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isTelephonyEnabled()) {
            mTelephonyManager!!.listen(mListener, PhoneStateListener.LISTEN_NONE)
        }
    }
}