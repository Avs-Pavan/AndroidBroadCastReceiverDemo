package com.kevin.androidbroadcastreceiverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.kevin.androidbroadcastreceiverdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    private val detector: AirPlaneModeDetector by lazy {
        AirPlaneModeDetector()
    }


    private val localBroadcastManager: LocalBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(this)
    }

    private val localBroadCast by lazy {
        LocalBroadCast()
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


        binding.register.setOnClickListener {
            localBroadcastManager.registerReceiver(localBroadCast, IntentFilter("Fruits"))
            toast(this, "Registered")
        }

        binding.trigger.setOnClickListener {
            val intent = Intent("Fruits").putExtra("data", "Apple")
            localBroadcastManager.sendBroadcast(intent)
        }
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(detector)

        unregisterReceiver(localBroadCast)
    }


    inner class LocalBroadCast : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                "Fruits" -> {
                    toast(this@MainActivity, "Broadcast received.")
                    binding.tv.text = intent.getStringExtra("data")
                }

                else -> {
                    binding.tv.text = ("No action found")
                }
            }
        }
    }


    private fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}