package com.example.hotel_brookes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotel_brookes.databinding.ActivityAdminHomeBinding
import com.example.hotel_brookes.databinding.ActivityRoomBinding

class AdminHome : AppCompatActivity() {

    private lateinit var binding: ActivityAdminHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton2.setOnClickListener {
            val viewBooking = Intent(this, ViewBooking::class.java)
            startActivity(viewBooking)
        }


        binding.imageButton.setOnClickListener {
            val signupIntent = Intent(this, DataAddActivity::class.java)
            startActivity(signupIntent)
        }
        }

    }
