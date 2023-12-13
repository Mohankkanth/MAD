package com.example.hotel_brookes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotel_brookes.databinding.ActivityCancelBookingBinding
import com.example.hotel_brookes.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.snapshots

class CancelBooking : AppCompatActivity() {

    private lateinit var binding: ActivityCancelBookingBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var databasereference: DatabaseReference
    private lateinit var mailId: String
    private lateinit var roomno: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCancelBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelRoomBtn.setOnClickListener {
            roomno = binding.roomno.text.toString()
            mailId = binding.email.text.toString()
            if(roomno.isNotEmpty() && mailId.isNotEmpty())
            {

                deleteData(roomno,mailId)
//                readData(roomno)

                var intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "All fields are Required ", Toast.LENGTH_SHORT).show()
            }
        }





    }



    private fun updateData(category: String, roomNo: String, type: String) {
       var temp :String = "True"
        database = FirebaseDatabase.getInstance().getReference("Rooms")

        var new_data = mapOf<String,String>(

            "vacant" to temp,
            "category" to category,
            "room_no" to roomNo,
            "type" to type
        )

        database.child(roomNo!!).updateChildren(new_data).addOnSuccessListener {
            Toast.makeText(this, "Updated Successfully after cancellation", Toast.LENGTH_SHORT).show()
        }.addOnCanceledListener {
            Toast.makeText(this, "Updation Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteData(roomno: String, mailId: String) {
//        database = FirebaseDatabase.getInstance().getReference().child("userdata")




        databasereference = FirebaseDatabase.getInstance().getReference().child("userdata")
        databasereference.child(roomno).get().addOnSuccessListener {


            databasereference.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for (dataSnapShot in snapshot.children){
                            val patient = dataSnapShot.getValue(UserValues::class.java)
                            val temp: String = patient?.email.toString()
                            val temproom: String = patient?.roomno.toString()

//                            Toast.makeText(this@CancelBooking, "${temp}", Toast.LENGTH_SHORT).show()

                            if(temp.equals(mailId) && temproom.equals(roomno)  )
                            {
                                if(it.exists())
                                {
                                    val category:String = it.child("adult").value.toString()
                                    val room_no = it.child("roomno").value.toString()
                                    val type = it.child("type").value.toString()
//                val vacant = it.child("vacant").value.toString()
                                    updateData(category,room_no,type)
                                    databasereference.child(roomno).removeValue().addOnSuccessListener {
                                        Toast.makeText(this@CancelBooking, "Successfully Cancelled", Toast.LENGTH_SHORT).show()
                                    }.addOnFailureListener{
                                        Toast.makeText(this@CancelBooking, "Cancellation Failed", Toast.LENGTH_SHORT).show()
                                    }

                                }
                                else
                                {
                                    Toast.makeText(this@CancelBooking, "User doesn't exist", Toast.LENGTH_SHORT).show()
                                }
                            }

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@CancelBooking, error.toString(), Toast.LENGTH_SHORT).show()
                }
            })



        }.addOnFailureListener {
            Toast.makeText(this, "Failed To Retrieve", Toast.LENGTH_SHORT).show()
        }


        /*database.child(roomno).removeValue().addOnSuccessListener {
            Toast.makeText(this, "Successfully Cancelled", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Cancellation Failed", Toast.LENGTH_SHORT).show()
        }*/


    }
}