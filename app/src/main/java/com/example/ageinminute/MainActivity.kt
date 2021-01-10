package com.example.ageinminute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateSelectButton:Button=findViewById(R.id.dateButton)


        dateSelectButton.setOnClickListener{view->
            clickDatePicker(view)
        }

    }

      fun clickDatePicker(view: View?) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
            view, selectedYear, selctedMonth, selectedDay ->
            val selectedDateText = findViewById<TextView>(R.id.selectedDateText)
            val ageInMinutesText = findViewById<TextView>(R.id.AgeInMinuteText)
            val selectedDate ="$selectedDay/${selctedMonth+1}/$selectedYear"
            selectedDateText.setText(selectedDate)
            val sdf = SimpleDateFormat ("dd/MM/yyyy", Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)
            val dateInMinutes = theDate.time /60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time / 60000
            val differenceInMinutes = currentDateInMinutes - dateInMinutes
            ageInMinutesText.setText(differenceInMinutes.toString())



        },year,month,day)
        dpd.datePicker.maxDate = Date().time -86400000
        dpd.show()
    }

}