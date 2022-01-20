package com.priyanshu.connectivitycheck

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import java.lang.NullPointerException

class NetworkChangeReciever : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        try {
            if(isOnline(context)){
                val intent1 = Intent(context,LoadPage::class.java)
                startActivity(context,intent1,null)
//                Toast.makeText(context,"connected",Toast.LENGTH_LONG).show()
            }
            else{
                val intent2 = Intent(context,NoInternet::class.java)
                startActivity(context,intent2,null)
//                Toast.makeText(context,"Disconnected",Toast.LENGTH_LONG).show()
            }
        }
        catch (e : NullPointerException){
            e.printStackTrace()
        }
    }

     fun isOnline(context: Context) : Boolean{
        try {
            val connectivityManager : ConnectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            return networkCapabilities != null &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
        catch (e : Exception){
            e.printStackTrace()
   return false
        }
    }
}