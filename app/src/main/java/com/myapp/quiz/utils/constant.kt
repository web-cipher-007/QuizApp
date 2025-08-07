package com.myapp.quiz.model

data class Questions(
    val Id: Int,
    val Question: String,
    val image: Int?, // pass 0 if no image
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctAnswer: Int, // position (1-4)
    val category: String
)
