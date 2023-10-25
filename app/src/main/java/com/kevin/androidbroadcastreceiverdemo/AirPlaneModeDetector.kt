package com.kevin.androidbroadcastreceiverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirPlaneModeDetector : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val status = intent.getBooleanExtra("state", false)

        if (status) {
            toast(context, "Airplane mode enabled")
        } else
            toast(context, "Airplane mode disabled")

    }

    private fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}