package com.smdigital.doltinuku.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.location.places.ui.PlacePicker
import com.smdigital.doltinuku.R
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.content_post.*


class PostActivity : AppCompatActivity() {
    var PLACE_PICKER_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_outline_close)

        tetKategori.setOnClickListener {
            showSheet()
        }

        tvPickLoc.setOnClickListener {
            try {
                val intentBuilder = PlacePicker.IntentBuilder()
                val intent = intentBuilder.build(this)
                startActivityForResult(intent, PLACE_PICKER_REQUEST)
            } catch (e: GooglePlayServicesRepairableException) {
                GooglePlayServicesUtil
                        .getErrorDialog(e.connectionStatusCode, this, 0)
            } catch (e: GooglePlayServicesNotAvailableException) {
                Toast.makeText(this, "Google plaay service tidak tersedia", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                val place = PlacePicker.getPlace(this, data)
                val toastMsg = String.format("Place: %s", place.name)
                tvPickLoc.text = toastMsg
            }
        }
    }

    private fun showSheet() {
        val view = layoutInflater.inflate(R.layout.row_category, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }
}
