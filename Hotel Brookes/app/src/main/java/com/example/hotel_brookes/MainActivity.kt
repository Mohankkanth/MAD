package com.example.hotel_brookes

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.hotel_brookes.databinding.ActivityLoginBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)
//        iv_note.alpha=0f
//        iv_note.animate().setDuration(1500).alpha(1f).withEndAction{
//            val i=Intent(this,LoginActivity::class.java)
//            startActivity(i)
//            OverridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
//            finish()
//        }

//        binding..setOnClickListener {
//            Firebase.auth.signOut()
////            val signupIntent = Intent(this, Login::class.java)
////            startActivity(signupIntent)
        }
    }

