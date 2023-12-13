package com.example.hotel_brookes


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotel_brookes.databinding.ActivityRoomBinding
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import com.example.hotel_brookes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*











class RoomActivity : AppCompatActivity() {

    private lateinit var textDate: TextView
    private lateinit var textDate2: TextView
    private lateinit var binding: ActivityRoomBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textDate = findViewById(R.id.checkindate)
        textDate2 = findViewById(R.id.checkoutdate)
        val buttonDate = findViewById<ImageButton>(R.id.checkin)
        val buttonDate2 = findViewById<ImageButton>(R.id.checkout)
        val adultOptions = arrayOf("", "1", "2", "3", "4")
        val typeOptions = arrayOf("", "0","1", "2 ", "3", "4 ")
        val calendarBox = Calendar.getInstance()
        val dateBox = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            calendarBox.set(Calendar.YEAR, year)
            calendarBox.set(Calendar.MONTH, month)
            calendarBox.set(Calendar.DAY_OF_MONTH, day)
            updateText(calendarBox)
        }
        buttonDate.setOnClickListener {
            DatePickerDialog(
                this, dateBox, calendarBox.get(Calendar.YEAR), calendarBox.get(Calendar.MONTH),
                calendarBox.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val dateBox2 = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            calendarBox.set(Calendar.YEAR, year)
            calendarBox.set(Calendar.MONTH, month)
            calendarBox.set(Calendar.DAY_OF_MONTH, day)
            updateText2(calendarBox)
        }
        buttonDate2.setOnClickListener {
            DatePickerDialog(
                this, dateBox2, calendarBox.get(Calendar.YEAR), calendarBox.get(Calendar.MONTH),
                calendarBox.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


//        //////////////////////////////////////////////////////////////SPINNER/////////////////////////////////////////////////////////
        val spinner: Spinner = findViewById(R.id.spinner2)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, adultOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        val spinner2: Spinner = findViewById(R.id.spinner3)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, typeOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter2


        val button = findViewById<Button>(R.id.submitbutton)
        val conti = findViewById<EditText>(R.id.checkindate)
        val conti2 = findViewById<EditText>(R.id.checkoutdate)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)





///3333333333333333333333333333333333333333333333//////////////


        button.setOnClickListener {
            val selectedGender = when (radioGroup.checkedRadioButtonId) {
                R.id.radioButton1 -> "AC"
                R.id.radioButton2 -> "Non AC"
                else -> "Not Selected"
            }
            val text1 = conti.text.toString()
            val text2 = conti2.text.toString()
//            val radioButtonId = radioGroup.checkedRadioButtonId
//            val radioButton = findViewById<RadioButton>(radioButtonId).text.toString()
            if (text1.isEmpty() || text2.isEmpty()) {
                Toast.makeText(this, "Fields are Empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }





            val spinnerItem = spinner.selectedItem.toString()
            val spinnerItem2 = spinner2.selectedItem.toString()


            val intent = Intent(this, RoomDisplay::class.java)
            intent.putExtra("message", conti.text.toString())
            intent.putExtra("message2", conti2.text.toString())
            intent.putExtra("message3", selectedGender)
            intent.putExtra("message4", spinnerItem)
            intent.putExtra("message5", spinnerItem2)
            startActivity(intent)
        }

    }

    private fun updateText(calendar: Calendar) {
        val dateFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.UK)
        textDate.setText(sdf.format(calendar.time))
    }

    private fun updateText2(calendar: Calendar) {
        val dateFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.UK)
        textDate2.setText(sdf.format(calendar.time))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
//}