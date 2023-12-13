package com.example.hotel_brookes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.hotel_brookes.databinding.ActivityRoomDisplayBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RoomDisplay : AppCompatActivity() {



    private lateinit var binding: ActivityRoomDisplayBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private val studentList = mutableListOf<DataUser>()
    private lateinit var roomAdapter: ArrayAdapter<String>
    private lateinit var listView: ListView





    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_display)




        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Rooms")





        val intent = intent
        val message = intent.getStringExtra("message")
        val message2 = intent.getStringExtra("message2")
        val message3 = intent.getStringExtra("message3")
        val message4 = intent.getStringExtra("message4")
        val message5 = intent.getStringExtra("message5")
        val textView = findViewById<TextView>(R.id.textView10)
        val textView2 = findViewById<TextView>(R.id.chout)
        val textView3 = findViewById<TextView>(R.id.adul)
        val textView4 = findViewById<TextView>(R.id.kid)
        val textView5 = findViewById<TextView>(R.id.type)
        textView.text = message
        textView2.text = message2
        textView3.text = message3
        textView4.text = message4
        textView5.text = message5

        val button=findViewById<Button>(R.id.display)
        button.setOnClickListener {

            val temp = textView3.text.toString()
            val temp2 = textView4.text.toString()

            checkdata(temp,temp2)

//            val listView = findViewById<ListView>(R.id.listrooms)
            listView = findViewById(R.id.listrooms)
            roomAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
            listView.adapter = roomAdapter
//            listView.setOnItemClickListener { _, _, position, _ ->
//                val selectedCountry = roomAdapter[position]
//                val intent = Intent(this, UserBooking::class.java)
//                intent.putExtra("countryName", selectedCountry)
//                startActivity(intent)
//            }
            listView.setOnItemClickListener  { _, _, position, _ ->
                val selectedRoomNumber = roomAdapter.getItem(position)
                val intent = Intent(this, UserBooking::class.java)
                intent.putExtra("selectedRoomNumber", selectedRoomNumber)
                intent.putExtra("checkindate", textView.text.toString())
                intent.putExtra("checkoutdate", textView2.text.toString())
                intent.putExtra("type", textView3.text.toString())
                intent.putExtra("adults", textView4.text.toString())
                intent.putExtra("kids", textView5.text.toString())
                startActivity(intent)
            }

        }

    }



    private fun checkdata(type :String,category :String ){
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                roomAdapter.clear()
                for (data in dataSnapshot.children) {
                    val room = data.getValue(DataUser::class.java)
                    var student = room?.vacant
                    val typed = room?.type
                    val categoryd = room?.category
                    if (student.equals("True") && typed.equals(type) && categoryd.equals(category)) {
//                        if (typed.equals(type)) {
//                            if (categoryd.equals(category)) {
//                                studentList.add(room.room_no)

//                                    room?.vacant = "False";
//                        var temp = "False"
//                        student = "False"
//                        updataData(student)
////                                room.room_no?.let { studentList.add(it) }
//                                return
                        val number = room?.room_no
                        number?.let { roomAdapter.add(it.toString()) }
//                            }
//                        }
                    }
                    // Now, studentList contains the student details from Firebase
                }
//                return
            }


            override fun onCancelled(databaseError: DatabaseError) {


            }

            /*private fun updataData(student : String){
                databaseReference = FirebaseDatabase.getInstance().getReference("Rooms")
//                student = temp
                var new_data = mapOf<String,String>(

                    "student" to student
                )
                databaseReference.child(type).updateChildren(new_data).addOnSuccessListener {
                    Toast.makeText(this@RoomDisplay, "Updated Successfully", Toast.LENGTH_SHORT).show()
                }.addOnCanceledListener {
                    Toast.makeText(this@RoomDisplay, "Updation Failed", Toast.LENGTH_SHORT).show()
                }
            }*/
        })
    }




}