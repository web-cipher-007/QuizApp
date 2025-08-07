package com.myapp.quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.myapp.quiz.setOnClickListener
import com.myapp.quiz.utils.ActivityCategory3

// this is for EnterYourName page
private fun Button.setOnClickListener(l: (View) -> Unit) {}

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val startButton: Button = findViewById(R.id.submitButton)
        val editTextName: EditText = findViewById(R.id.inputField)

        startButton.setOnClickListener {
            if (!editTextName.text.isEmpty()) {
                val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("username", editTextName.text.toString().trim() )
                editor.apply()

                val intent = Intent(this@MainActivity2, ActivityCategory3::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@MainActivity2, "Please enter your name", Toast.LENGTH_LONG).show()
            }
        }

    }
}