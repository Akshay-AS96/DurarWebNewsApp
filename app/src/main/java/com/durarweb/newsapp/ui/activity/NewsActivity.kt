package com.durarweb.newsapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.durarweb.newsapp.R
import com.durarweb.newsapp.databinding.ActivityNewsBinding
import com.durarweb.newsapp.ui.fragment.NewsFragment
import com.durarweb.newsapp.utils.InternetConnectivityUtils

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private val internetConnectivityUtils by lazy { InternetConnectivityUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        if (savedInstanceState == null) {
            val fragment = NewsFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
        internetCheckup()
    }

    private fun internetCheckup() {
        internetConnectivityUtils.observe(this) {
            Log.e("TAG", "onCreate: $it")
            if (it) {
                // Internet is available
                binding.clMain.visibility = View.GONE
                binding.container.root.visibility = View.VISIBLE
            } else {
                // Internet is not available
                binding.clMain.visibility = View.VISIBLE
                binding.container.root.visibility = View.INVISIBLE
            }
        }
    }
}