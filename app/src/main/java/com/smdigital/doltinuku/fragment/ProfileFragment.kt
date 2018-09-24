package com.smdigital.doltinuku.fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.PopupMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.activity.EditProfileActivity
import com.smdigital.doltinuku.adapter.SectionPagerAdapter
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment(), PopupMenu.OnMenuItemClickListener {

    var status = ""
    private var mSectionsPagerAdapter: SectionPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val views = inflater.inflate(R.layout.fragment_profile, container, false)
/*        view?.btnMasuk?.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
            (context as Activity).overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)
        }

        view?.btnDaftar?.setOnClickListener {
            startActivity(Intent(activity, RegisterActivity::class.java))
            (context as Activity).overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)

        }*/
        (activity as AppCompatActivity).setSupportActionBar(views.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.title = null
        views.appBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout!!.totalScrollRange
                }

                if (scrollRange + verticalOffset == 0) {
                    views.collapsingToolbar.title = "Saya Juragan"
                    isShow = true
                } else if (isShow) {
                    views.collapsingToolbar.title = null
                    isShow = false
                }
            }
        })
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

        mSectionsPagerAdapter = SectionPagerAdapter(childFragmentManager)
        views.containPager.adapter = mSectionsPagerAdapter
        views.tabs.setupWithViewPager(views.containPager)

        views.ibSetting.setOnClickListener { view -> showPopup(view) }

        return views
    }

    private fun showPopup(view: View) {
        val popup = PopupMenu(activity!!, view)
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.menu_profile)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_change -> {
                startActivity(Intent(activity, EditProfileActivity::class.java))
                return true
            }
            R.id.action_logout -> {
                Toast.makeText(activity, "KELUAR", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return false
        }
    }
}