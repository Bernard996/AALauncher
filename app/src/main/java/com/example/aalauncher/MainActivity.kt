package com.example.aalauncher

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.GET_META_DATA
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var drawerButton : ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerButton = findViewById(R.id.drawerButton)

        drawerButton?.setOnClickListener {
            var intent = Intent(this, AppsDrawer::class.java)
            startActivity(intent)
        }

    }
}