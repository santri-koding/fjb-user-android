package com.smdigital.doltinuku.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.helper.PermissionHelper
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.content_edit_profile.*

class EditProfileActivity : AppCompatActivity() {

    private var permissionHelper: PermissionHelper? = null
    private var REQUEST_CAMERA: Int? = 2
    private var SELECT_FILE: Int? = 3
    private var REQUEST_CAMERA_2: Int? = 4
    private var SELECT_FILE_2: Int? = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_outline_close)

        permissionHelper = PermissionHelper(this)

        btnUp.setOnClickListener {
            checkAndRequestPermissions()
        }

        btnEditCover.setOnClickListener {
            checkAndRequestPermission()
        }

        btnEditCover
    }

    private fun checkAndRequestPermissions() {
        permissionHelper?.permissionListener(object : PermissionHelper.PermissionListener {
            override fun onPermissionCheckDone() {
                selectImage()
            }
        })

        permissionHelper?.checkAndRequestPermissions()
    }

    private fun checkAndRequestPermission() {
        permissionHelper?.permissionListener(object : PermissionHelper.PermissionListener {
            override fun onPermissionCheckDone() {
                takeImage()
            }
        })

        permissionHelper?.checkAndRequestPermissions()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHelper?.onRequestCallBack(requestCode, permissions, grantResults)
    }

    private fun selectImage() {
        val items = arrayOf<CharSequence>("Kamera", "Galeri", "Batal")
        val builder = AlertDialog.Builder(this@EditProfileActivity)
        builder.setTitle("Upload image")
        builder.setItems(items) { dialogInterface, i ->
            if (items[i] == "Kamera") {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, this.REQUEST_CAMERA!!)
            } else if (items[i] == "Galeri") {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                startActivityForResult(intent, this.SELECT_FILE!!)
            } else if (items[i] == "Batal") {
                dialogInterface.dismiss()
            }
        }
        builder.show()

    }

    private fun takeImage() {
        val items = arrayOf<CharSequence>("Kamera", "Galeri", "Batal")
        val builder = AlertDialog.Builder(this@EditProfileActivity)
        builder.setTitle("Upload Image")
        builder.setItems(items) { dialogInterface, i ->
            if (items[i] == "Kamera") {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, this.REQUEST_CAMERA_2!!)
            } else if (items[i] == "Galeri") {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                startActivityForResult(intent, this.SELECT_FILE_2!!)
            } else if (items[i] == "Batal") {
                dialogInterface.dismiss()
            }
        }
        builder.show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                val bundle = data?.extras
                val bmp = bundle!!.get("data") as Bitmap
                civPoto.setImageBitmap(bmp)
            } else if (requestCode == SELECT_FILE) {
                val selectedImageUri = data?.data
                civPoto.setImageURI(selectedImageUri)
            }

            if (requestCode == REQUEST_CAMERA_2) {
                val bundles = data?.extras
                val bmps = bundles!!.get("data") as Bitmap
                ivFullCover.setImageBitmap(bmps)
            } else if (requestCode == SELECT_FILE_2) {
                val selectedImagesUri = data?.data
                ivFullCover.setImageURI(selectedImagesUri)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_edit_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
