package com.example.hotel_brookes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotel_brookes.databinding.ActivityHomeBinding
import com.example.hotel_brookes.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.button4.setOnClickListener {
            val singleintent = Intent(this,RoomActivity::class.java)
            startActivity(singleintent)
        }

        binding.button2.setOnClickListener{
            val Intent=Intent(this,AboutUs::class.java)
            startActivity(Intent)
        }

        binding.cancelBtn.setOnClickListener{
            val Intent=Intent(this,CancelBooking::class.java)
            startActivity(Intent)
        }


        binding.button3.setOnClickListener{
            val Intent=Intent(this,Gallery::class.java)
            startActivity(Intent)
        }

//        firebaseAuth = FirebaseAuth.getInstance()
//        binding.doublebtn.setOnClickListener {
//            val doubleintent = Intent(this, DoubleActivity::class.java)
//            startActivity(doubleintent)
//        }
//
//        firebaseAuth = FirebaseAuth.getInstance()
//        binding.familybtn.setOnClickListener {
//            val familyintent = Intent(this, FamilyActivity::class.java)
//            startActivity(familyintent)
//        }
//        binding.logoutbtn.setOnClickListener {
//            firebaseAuth.signOut()
//            startActivity(
//                Intent(
//                    this,
//                    LoginActivity::class.java
//                )
//            )
//            finish()
        }
    }
