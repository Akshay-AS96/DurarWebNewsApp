package com.durarweb.newsapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.durarweb.newsapp.R
import com.durarweb.newsapp.databinding.ActivityDashboardBinding
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private var backPressed: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(this) {
            if (backPressed + 2000 > System.currentTimeMillis()) {
                finish()
            } else {
                val snackBar = Snackbar.make(
                    binding.toolbar, getString(R.string.back_press_text),
                    Snackbar.LENGTH_SHORT
                )
                snackBar.show()
                backPressed = System.currentTimeMillis()
            }
        }
    }

    fun onButton1Click(view: View) {
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
    }

    fun onButton2Click(view: View) {
        val intent = Intent(this, HeadlinesActivity::class.java)
        startActivity(intent)
    }
}