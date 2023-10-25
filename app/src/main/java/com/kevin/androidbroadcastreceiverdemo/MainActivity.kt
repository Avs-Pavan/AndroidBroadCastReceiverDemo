package com.kevin.androidbroadcastreceiverdemo

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.androidbroadcastreceiverdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val detector: AirPlaneModeDetector by lazy {
        AirPlaneModeDetector()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(detector, it)
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(detector)
    }

}