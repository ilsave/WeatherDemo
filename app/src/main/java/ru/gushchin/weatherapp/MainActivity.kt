package ru.gushchin.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initNavManager()
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
//        val request = NavDeepLinkRequest.Builder
//            .fromUri("android-app://example.google.app/detail_fragment".toUri())
//            .build()
        //navHostFragment?.findNavController()?.navigate(request)
    }

}