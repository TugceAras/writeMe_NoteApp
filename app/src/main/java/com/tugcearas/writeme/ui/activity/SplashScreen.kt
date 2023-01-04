package com.tugcearas.writeme.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.tugcearas.writeme.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window?.statusBarColor = ContextCompat.getColor(this, R.color.background_color)
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
           startActivity(Intent(this,MainActivity::class.java))
            finish()
        },1000)
    }
}