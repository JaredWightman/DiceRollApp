package com.example.dicerollapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class OneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_one)

        // Setup for xml object ID's
        val mutton : Button = findViewById(R.id.mutton)
        val nutton : Button = findViewById(R.id.nutton)
        val glutton : Button = findViewById(R.id.glutton)
        val chutton : Button = findViewById(R.id.chutton)
        val strutton : Button = findViewById(R.id.strutton)
        val output : TextView = findViewById(R.id.outputText2)

        // Reads and displays die type that came in from other activity
        val incoming = intent
        var dieType = incoming.getIntExtra("die",20)
        output.text = "D$dieType"

        // Switches activity and sends over die type when button pressed
        mutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("die",dieType)
            startActivity(intent)
        }

        // Sets die type to D2, displays
        nutton.setOnClickListener {
            dieType = 2
            output.text = "D$dieType"
        }

        // Sets die type to D6, displays
        glutton.setOnClickListener {
            dieType = 6
            output.text = "D$dieType"
        }

        // Sets die type to D10, displays
        chutton.setOnClickListener {
            dieType = 10
            output.text = "D$dieType"
        }

        // Sets die type to D20, displays
        strutton.setOnClickListener {
            dieType = 20
            output.text = "D$dieType"
        }
    }
}

