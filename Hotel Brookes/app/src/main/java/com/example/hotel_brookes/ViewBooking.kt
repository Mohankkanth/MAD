package com.example.hotel_brookes



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class ViewBooking:AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var requestArrayList: ArrayList<UserValues>
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: DatabaseReference
    private lateinit var currentBlood: String
    private lateinit var tempBloodDonate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_booking)
//        tempBloodDonate = intent.getStringExtra("BloodTypeDash").toString()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        requestArrayList = arrayListOf()

        database = FirebaseDatabase.getInstance().getReference("userdata")
        // Define your query
//        val query: Query = database.orderByChild("bloodType").equalTo(tempBloodDonate)

//        Toast.makeText(this, "${tempBlood}  ,   ${currentBlood}", Toast.LENGTH_SHORT).show()

//        firebaseDatabase = FirebaseDatabase.getInstance()
//        databaseReference = firebaseDatabase.reference.child("BloodNeedy").child("bloodType")
//        currentBlood = databaseReference.toString()

        database.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (dataSnapShot in snapshot.children){
                        val patient = dataSnapShot.getValue(UserValues::class.java)
//                        val temp: String = patient?.name.toString()

                            if(!requestArrayList.contains(patient)){
                                requestArrayList.add(patient!!)
                            }

                    }
                    recyclerView.adapter = MyAdapter(requestArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewBooking, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })


        /* val adapter = MyAdapter(requestArrayList) { selectedName ->
             val intent = Intent(this@DonateBlood,AcceptToDonate::class.java )
             intent.putExtra("selectedName",selectedName)
             startActivity(intent)
     }

     recyclerView.adapter = adapter*/

//        recyclerView.setOnClickListener {,_,position, ->
//            val temp = requestArrayList.getItem(position)
//        }



//        Toast.makeText(this, "${tempBlood} = Donor blood, ${currentBlood} = RequestBlood", Toast.LENGTH_SHORT).show()

    }



}