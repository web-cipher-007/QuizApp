package com.myapp.quiz.utils

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.myapp.quiz.R
import com.myapp.quiz.ui.quizstart
import androidx.cardview.widget.CardView


// this page displays category page

class ActivityCategory3 : AppCompatActivity() {

    private val categoryMap = mapOf(
        R.id.GK to "General-Knowledge",
        R.id.Physics to "Physics",
        R.id.Chemistry to "Chemistry",
        R.id.Biology to "Biology",
        R.id.Maths to "Mathematics",
        R.id.Engg to "Engineering",
        R.id.CS to "Computer Science",
        R.id.GP to "Geo-Politics",
        R.id.Sports to "Sports",
        R.id.Software to "Software & Tech",
        R.id.History to "History",
        R.id.IQ to "IQ"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        for ((cardId, categoryName) in categoryMap) {
            val cardView = findViewById<CardView>(cardId)
            cardView.setOnClickListener {
                val intent = Intent(this@ActivityCategory3, quizstart::class.java)
                intent.putExtra("category", categoryName)
                startActivity(intent)
            }




        }
    }
}