package com.example.hotel_brookes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserBooking : AppCompatActivity() {

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_booking)


        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("userdata")


        val message = intent.getStringExtra("selectedRoomNumber")
        val message2 = intent.getStringExtra("checkindate")
        val message3 = intent.getStringExtra("checkoutdate")
        val message4 = intent.getStringExtra("type")
        val message5 = intent.getStringExtra("adults")
        val message6 = intent.getStringExtra("kids")
        val TextView = findViewById<TextView>(R.id.roomNumberTextView)
        val TextView2 = findViewById<TextView>(R.id.roomNumberTextView2)
        val TextView3 = findViewById<TextView>(R.id.roomNumberTextView3)
        val TextView4 = findViewById<TextView>(R.id.roomNumberTextView4)
        val TextView5 = findViewById<TextView>(R.id.roomNumberTextView5)
        val TextView6 = findViewById<TextView>(R.id.roomNumberTextView6)

        TextView.text = message
        TextView2.text = message2
        TextView3.text = message3
        TextView4.text = message4
        TextView5.text = message5
        TextView6.text = message6




        val button=findViewById<Button>(R.id.Booking)
        val textname = findViewById<TextView>(R.id.name)
        val textmobile = findViewById<TextView>(R.id.mobile)
        val textaadhar = findViewById<TextView>(R.id.aadhar)
        val textemail = findViewById<TextView>(R.id.email)


        button.setOnClickListener {

            val name = textname.text.toString()
            val mobile = textmobile.text.toString()
            val aadhar = textaadhar.text.toString()
            val email = textemail.text.toString()
            val roomno = TextView.text.toString()
            val checkin = TextView2.text.toString()
            val checkout = TextView3.text.toString()
            val type = TextView4.text.toString()
            val adult = TextView5.text.toString()
            val kids = TextView6.text.toString()

            if(name.isNotEmpty() && mobile.isNotEmpty() && aadhar.isNotEmpty() && email.isNotEmpty() && roomno.isNotEmpty() && checkin.isNotEmpty() && checkout.isNotEmpty() && type.isNotEmpty() && adult.isNotEmpty() && kids.isNotEmpty()){
                insertdata(name,mobile,aadhar,email,roomno,checkin,checkout,type,adult,kids)
                var student = "False"
                updateData(student,roomno,adult,type)

                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("mailId",email)
                startActivity(intent)
            }else{
                Toast.makeText(this@UserBooking,"ALL FIELDS ARE REQUIRED", Toast.LENGTH_SHORT).show()
            }


        }


    }

    private fun updateData(student: String,roomno: String,adult:String,type: String) {
        database = FirebaseDatabase.getInstance().getReference("Rooms")
//                student = temp
        var new_data = mapOf<String,String>(

            "vacant" to student,
            "category" to adult,
            "room_no" to roomno,
            "type" to type
        )
        database.child(roomno!!).updateChildren(new_data).addOnSuccessListener {
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
        }.addOnCanceledListener {
            Toast.makeText(this, "Updation Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertdata(name :String,mobile :String,aadhar :String,email :String,roomnumber :String,checkin :String,checkout :String,type :String,adults :String,kids :String){
        databaseReference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if(!datasnapshot.exists()){
                    val userData = UserValues(name,mobile,aadhar,email,roomnumber,checkin,checkout,type,adults,kids)
                    databaseReference.child(roomnumber!!).setValue(userData)
                    Toast.makeText(this@UserBooking,"SUCCESSFULL", Toast.LENGTH_SHORT).show()
                    finish()

//                    var mailId = Intent(this@UserBooking,email)
                }else{
                    Toast.makeText(this@UserBooking,"USER ALREADY EXIST", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@UserBooking,"DATABASE ERROR: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}