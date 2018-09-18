package com.smdigital.doltinuku.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.fragment.FeedFragment
import com.smdigital.doltinuku.fragment.HomeFragment
import com.smdigital.doltinuku.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(HomeFragment(), R.id.frame)
                floatingActionButton.hide()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_post -> {
                replaceFragment(FeedFragment(), R.id.frame)
                floatingActionButton.hide()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                replaceFragment(ProfileFragment(), R.id.frame)
                floatingActionButton.show()
                return@OnNavigationItemSelectedListener true
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(HomeFragment(), R.id.frame)
        floatingActionButton.hide()
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java))
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right).func().commit()
    }

    private fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    private fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
    }
}
