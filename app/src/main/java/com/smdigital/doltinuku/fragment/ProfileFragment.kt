package com.smdigital.doltinuku.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.smdigital.doltinuku.activity.LoginActivity
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.activity.RegisterActivity
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        view.btnDaftar.setOnClickListener {
            Toast.makeText(activity, "DAFTAR", Toast.LENGTH_LONG).show()
        }

        view.btnMasuk.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
        }

        view.btnDaftar.setOnClickListener {
            startActivity(Intent(activity, RegisterActivity::class.java))
        }
        return view
    }

}