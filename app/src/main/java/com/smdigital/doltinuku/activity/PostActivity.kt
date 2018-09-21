package com.smdigital.doltinuku.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.location.places.ui.PlacePicker
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.helper.PermissionHelper
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.content_post.*
import kotlinx.android.synthetic.main.row_category.view.*
import java.text.NumberFormat
import java.util.*

class PostActivity : AppCompatActivity() {
    private var PLACE_PICKER_REQUEST = 1
    private var permissionHelper: PermissionHelper? = null
    private var REQUEST_CAMERA: Int? = 1
    private var SELECT_FILE: Int? = 0


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

        permissionHelper = PermissionHelper(this)

        tvUp.setOnClickListener {
            checkAndRequestPermissions()
        }

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

        setCurrency(tetHarga)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                val place = PlacePicker.getPlace(this, data)
                val toastMsg = String.format("%s", place.name)
                tvPickLoc.text = toastMsg
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                val bundle = data?.extras
                val bmp = bundle!!.get("data") as Bitmap
                ivUp.setImageBitmap(bmp)
            } else if (requestCode == SELECT_FILE) {
                val selectedImageUri = data?.getData()
                ivUp.setImageURI(selectedImageUri)
            }

        }
    }

    private fun showSheet() {
        val view = layoutInflater.inflate(R.layout.row_category, null)
        val dialog = BottomSheetDialog(this)
        var kategori: String
        view.tvFashion.setOnClickListener {
            kategori = "Fashion"
            tetKategori.setText(kategori, TextView.BufferType.EDITABLE)
            dialog.dismiss()
        }

        view.tvElektronik.setOnClickListener {
            kategori = "Elektronik"
            tetKategori.setText(kategori, TextView.BufferType.EDITABLE)
            dialog.dismiss()
        }

        view.tvOtomotif.setOnClickListener {
            kategori = "Otomotif"
            tetKategori.setText(kategori, TextView.BufferType.EDITABLE)
            dialog.dismiss()
        }

        view.tvKecantikan.setOnClickListener {
            kategori = "Kecantikan & Kesehatan"
            tetKategori.setText(kategori, TextView.BufferType.EDITABLE)
            dialog.dismiss()
        }

        view.tvAksesoris.setOnClickListener {
            kategori = "Aksesoris"
            tetKategori.setText(kategori, TextView.BufferType.EDITABLE)
            dialog.dismiss()
        }

        view.tvPeralatan.setOnClickListener {
            kategori = "Peralatan rumah tangga"
            tetKategori.setText(kategori, TextView.BufferType.EDITABLE)
            dialog.dismiss()
        }
        dialog.setContentView(view)
        dialog.show()
    }

    private fun setCurrency(edt: EditText) {
        edt.addTextChangedListener(object : TextWatcher {
            private var current = ""

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString() != current) {
                    edt.removeTextChangedListener(this)

                    val local = Locale("id", "id")
                    val replaceable = String.format("[Rp,.\\s]",
                            NumberFormat.getCurrencyInstance().getCurrency()
                                    .getSymbol(local))
                    val cleanString = s.toString().replace(replaceable.toRegex(), "")

                    var parsed: Double
                    try {
                        parsed = java.lang.Double.parseDouble(cleanString)
                    } catch (e: NumberFormatException) {
                        parsed = 0.00
                    }

                    val formatter = NumberFormat
                            .getCurrencyInstance(local)
                    formatter.maximumFractionDigits = 0
                    formatter.isParseIntegerOnly = true
                    val formatted = formatter.format(parsed)

                    val replace = String.format("[Rp\\s]",
                            NumberFormat.getCurrencyInstance().currency
                                    .getSymbol(local))
                    val clean = formatted.replace(replace.toRegex(), "")

                    current = formatted
                    edt.setText(clean)
                    edt.setSelection(clean.length)
                    edt.addTextChangedListener(this)
                }
            }
        })
    }

    private fun checkAndRequestPermissions() {
        permissionHelper?.permissionListener(object : PermissionHelper.PermissionListener {
            override fun onPermissionCheckDone() {
                selectImage()
            }
        })

        permissionHelper?.checkAndRequestPermissions()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHelper?.onRequestCallBack(requestCode, permissions, grantResults)
    }

    private fun selectImage() {

        val items = arrayOf<CharSequence>("Camera", "Gallery", "Cancel")

        val builder = AlertDialog.Builder(this@PostActivity)
        builder.setTitle("Add Image")

        builder.setItems(items) { dialogInterface, i ->
            if (items[i] == "Camera") {

                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, this.REQUEST_CAMERA!!)

            } else if (items[i] == "Gallery") {

                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                startActivityForResult(intent, this.SELECT_FILE!!)

            } else if (items[i] == "Cancel") {
                dialogInterface.dismiss()
            }
        }
        builder.show()

    }
}
