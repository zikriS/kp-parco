package com.example.parcocoon

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.parcocoon.Adapter.ViewPagerAdapter
import com.example.parcocoon.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fragmentA = FoodFragment()
        val fragmentB = DiskusiFragment()
        val fragmentC = KesehatanFragment()

        replaceFragment(fragmentA)
        binding.btmNavViewActvity.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_food -> replaceFragment(fragmentA)
                R.id.nav_chat -> replaceFragment(fragmentB)
                R.id.nav_health -> replaceFragment(fragmentC)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val Fragment = supportFragmentManager
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLActivity, fragment).commit()
        }

    }

    private fun setupTab() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FoodFragment(), "")
        adapter.addFragment(DiskusiFragment(), "")
        adapter.addFragment(KesehatanFragment(), "")

//        binding.frameLActivity.adapter = adapter
//        binding.btmNavViewActvity.setupWithframeLActivity(binding.frameLActivity)
//
//        binding.btmNavViewActvity.getTabAt
    }

}