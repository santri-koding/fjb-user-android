package com.smdigital.doltinuku.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.activity.LoginActivity
import com.smdigital.doltinuku.activity.RegisterActivity
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    var status = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
/*        view?.btnMasuk?.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
            (context as Activity).overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)
        }

        view?.btnDaftar?.setOnClickListener {
            startActivity(Intent(activity, RegisterActivity::class.java))
            (context as Activity).overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)

        }*/

        status = "MASUK"

/*        if (status == "MASUK") {
            view?.frameProfil?.setBackgroundColor(ContextCompat.getColor(activity!!.applicationContext, R.color.colorWindowBackground))
            view?.hasLogin?.visibility = View.VISIBLE
            view?.notLogin?.visibility = View.GONE
        } else {
            view?.frameProfil?.setBackgroundColor(ContextCompat.getColor(activity!!.applicationContext, R.color.colorComponen))
            view?.notLogin?.visibility = View.VISIBLE
            view?.hasLogin?.visibility = View.GONE
        }*/

        return view
    }
}