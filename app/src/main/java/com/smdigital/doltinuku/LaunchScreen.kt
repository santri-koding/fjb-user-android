package com.smdigital.doltinuku

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.smdigital.doltinuku.activity.MainActivity

class LaunchScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}