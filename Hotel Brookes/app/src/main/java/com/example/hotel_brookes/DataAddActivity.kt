package com.example.hotel_brookes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
//import com.example.first.databinding.ActivityCheckListBinding
import com.example.hotel_brookes.databinding.ActivityDataAddBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataAddBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataAddBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        val spinner: Spinner = findViewById(R.id.spinner2)
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, adultOptions)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = adapter




        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Rooms")

        binding.addbutton.setOnClickListener {
            val roomno = binding.roomNo.text.toString()
            val category = binding.category.text.toString()
            val type = binding.type.text.toString()
            val vacant = binding.vacant.text.toString()

            if(roomno.isNotEmpty() && category.isNotEmpty() && type.isNotEmpty() && vacant.isNotEmpty()){
                insertdata(roomno,category,type,vacant)
            }else{
                Toast.makeText(this@DataAddActivity,"ALL FIELDS ARE REQUIRED", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun insertdata(roomno: String,category: String,type: String,vacant: String){
        databaseReference.orderByChild("name").equalTo(roomno).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if(!datasnapshot.exists()){
//                    val id = "501"
                    val userData = DataUser(roomno,category,type,vacant)
                    databaseReference.child(roomno!!).setValue(userData)
                    Toast.makeText(this@DataAddActivity,"SUCCESSFULL", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this@DataAddActivity,"USER ALREADY EXIST", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@DataAddActivity,"DATABASE ERROR: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


}