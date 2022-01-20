package com.priyanshu.connectivitycheck

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {
    lateinit var MyBroadcastReciever : NetworkChangeReciever
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyBroadcastReciever = NetworkChangeReciever()
        registerNetworkReciever()
    }

    fun registerNetworkReciever(){
        registerReceiver(MyBroadcastReciever,IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }
    fun unregisterNetwork(){
        try {
            unregisterReceiver(MyBroadcastReciever)
        }
        catch (e : IllegalArgumentException){
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetwork()
    }
}