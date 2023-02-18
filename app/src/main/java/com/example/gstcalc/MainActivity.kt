package com.example.gstcalc

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.amount)
        val switchCompat = findViewById<SwitchCompat>(R.id.switch1)
        val buttonView = findViewById<Button>(R.id.button)
        val resultTextView = findViewById<TextView>(R.id.result_textview)
        switchCompat.setOnCheckedChangeListener { _, isChecked ->
            switchCompat.isEnabled = isChecked
        }

        //dictate what to do regarding user controls
        buttonView.setOnClickListener {
            val input = editText.text?.toString()?.toLong() ?: -1
            val en = switchCompat.isEnabled
            val gstRate = if(en) 1.09f else 1.08f
            val result = input * gstRate

            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING

            resultTextView.text = "Price: $" + df.format(result)
            resultTextView.textSize = 24f
            resultTextView.setTextColor(Color.DKGRAY)
            //only after onClick
            Toast.makeText(baseContext, "Click", Toast.LENGTH_LONG).show()
        }
    }
}