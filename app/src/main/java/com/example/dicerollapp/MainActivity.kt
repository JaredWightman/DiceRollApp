package com.example.dicerollapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    // Function that runs on startup, like Main().
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Setup for Die type, xml object ID's, history, and a sound player
        val incoming = intent
        val dieType = incoming.getIntExtra("die",20)
        val rutton : Button = findViewById(R.id.rutton)
        val cutton : Button = findViewById(R.id.cutton)
        rutton.text = "Roll D$dieType"
        val mP = MediaPlayer.create(applicationContext,R.raw.laughtrack)
        var history = ""
        var prev = ""

        // Switches activity and sends over die type when button pressed
        cutton.setOnClickListener {
            val intent = Intent(this, OneActivity::class.java)
            intent.putExtra("die", dieType)
            startActivity(intent)
        }

        // Logic for roll after center button pressed
        rutton.setOnClickListener {

            // Sets a random number based on die type, gets xml ID's, and shows loading icon
            val randInt = (1..dieType).random()
            val output : TextView = findViewById(R.id.outputText)
            val bar : ProgressBar = findViewById(R.id.progressBar)
            val histText : TextView = findViewById(R.id.historyText)
            output.visibility = View.GONE
            bar.visibility = View.VISIBLE

            // Keeping track of history, excluding current number
            prev = randInt.toString()
            output.text = prev
            rutton.setEnabled(false)
            histText.text = history

            // After a delay, plays a laugh track (if you get a 1 w/ D20), shows the number, and updates history
            val handler = android.os.Handler()
            handler.postDelayed({
                if ((dieType == 20) and (randInt == 1)) {
                    mP.start()
                }
                bar.visibility = View.GONE
                output.visibility = View.VISIBLE
                rutton.setEnabled(true)
                history = prev + "\n" +history
            }, 1500)
        }
    }
}

// VANITY/FUTURE:
// - resize and/or glide button when pressed
// - animate background
// - animate actual die rolling (would take SO much time)
// - track different players w/different colors
// - make a variable (riggable) probability system based on light level and noise level (requires camera and microphone access)