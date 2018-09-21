package com.smdigital.doltinuku.helper

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.StrictMode
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.smdigital.doltinuku.R
import java.util.*

class PermissionHelper(private val mActivity: Activity) {
    private val REQUEST_PERMISSION = 99
    private var listener: PermissionListener? = null

    fun permissionListener(permissionListener: PermissionListener) {
        listener = permissionListener
    }


    fun checkAndRequestPermissions() {
        //1. Call this to check permission. (Call this affected loop for check permission until user Approved it)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionCamera = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA)
            val permissionReadStorage = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
            val permissionWriteStorage = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)

            val listPermissionsNeeded = ArrayList<String>()

            if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CAMERA)
            }
            if (permissionReadStorage != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(mActivity, listPermissionsNeeded.toTypedArray(), REQUEST_PERMISSION)
                return
            }

        }

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        listener!!.onPermissionCheckDone()

    }

    fun onRequestCallBack(RequestCode: Int, permissions: Array<String>, grantResults: IntArray) {//2. call this inside onRequestPermissionsResult
        when (RequestCode) {
            REQUEST_PERMISSION -> {
                val perms = HashMap<String, Int>()
                // Initialize the map with both permissions
                perms[Manifest.permission.CAMERA] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.READ_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED
                // Fill with actual results from user
                if (grantResults.size > 0) {
                    for (i in permissions.indices)
                        perms[permissions[i]] = grantResults[i]
                    // Check for both permissions
                    val TAG = "PermissionHelper"
                    if (perms[Manifest.permission.CAMERA] == PackageManager.PERMISSION_GRANTED
                            && perms[Manifest.permission.READ_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED
                            && perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED) {
                        Log.e(TAG, "permission granted")

                        checkAndRequestPermissions()
                    } else {
                        Log.e(TAG, "Some permissions are not granted ask again ")
                        // permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
                        // shouldShowRequestPermissionRationale will return true
                        // show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.CAMERA) ||
                                ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                                || ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            showDialogOK(mActivity.getString(R.string.permission_dialog),
                                    DialogInterface.OnClickListener { dialog, which ->
                                        when (which) {
                                            DialogInterface.BUTTON_POSITIVE -> checkAndRequestPermissions()
                                        }//                                                case DialogInterface.BUTTON_NEGATIVE:
                                        //                                                    // proceed with logic by disabling the related features or quit the app.
                                        //                                                    break;
                                    })
                        } else {
                            Toast.makeText(mActivity, R.string.go_to_permissions_settings, Toast.LENGTH_LONG).show()
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts("package", mActivity.packageName, null)
                            intent.data = uri
                            mActivity.startActivity(intent)
                        }// permission is denied (and never ask again is  checked)
                        // shouldShowRequestPermissionRationale will return false
                    }
                }
            }
        }
    }

    private fun showDialogOK(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(mActivity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                //                .setNegativeButton("Cancel", okListener)
                .create()
                .show()
    }

    interface PermissionListener {
        fun onPermissionCheckDone()
    }

}