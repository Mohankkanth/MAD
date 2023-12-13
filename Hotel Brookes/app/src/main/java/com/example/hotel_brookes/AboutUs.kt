package com.example.hotel_brookes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        val second=findViewById<ImageButton>(R.id.imageButton2)
        second.setOnClickListener{
            val Intent=Intent(this,HomeActivity::class.java)
            startActivity(Intent)
        }
    }
}