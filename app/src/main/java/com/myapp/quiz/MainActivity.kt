package com.myapp.quiz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowInsetsCompat


// this is for welcome screen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // Forces Light Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        progressBar.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.visibility = View.GONE

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish() }, 4000) // 4000 milliseconds = 4 seconds


        val welcomeTextView = findViewById<TextView>(R.id.welcomeText)
        typeWriterEffect(welcomeTextView, "Welcome to")
        val titleTextView = findViewById<TextView>(R.id.appTitle)
        typeWriterEffect(titleTextView, "QuizMania", delay = 110L)

    }
    private fun typeWriterEffect(textView: TextView, text: String, delay: Long = 100L) {
        var i = 0
        val handler = Handler(Looper.getMainLooper())

        val runnable = object : Runnable {
            override fun run() {
                if (i <= text.length) {
                    textView.text = text.substring(0, i)
                    i++
                    handler.postDelayed(this, delay)
                }
            }
        }

        handler.post(runnable)
    }


}