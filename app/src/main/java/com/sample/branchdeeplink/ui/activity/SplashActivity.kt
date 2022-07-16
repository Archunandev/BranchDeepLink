package com.sample.branchdeeplink.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sample.branchdeeplink.R

class SplashActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            startActivity(
                Intent(this,
                    DashboardActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }, 4500)

    }
}