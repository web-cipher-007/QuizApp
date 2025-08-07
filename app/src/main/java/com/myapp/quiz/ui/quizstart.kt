package com.myapp.quiz.ui

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.myapp.quiz.R
import com.myapp.quiz.model.Questions
import com.myapp.quiz.model.*

class quizstart : AppCompatActivity() {

    private lateinit var questions: List<Questions>
    private var currentQuestionIndex = 0
    private var score = 0
    private var selectedAnswer = 0

    // UI Components
    private lateinit var categoryTitle: TextView
    private lateinit var userName: TextView
    private lateinit var questionText: TextView
    private lateinit var option1: TextView
    private lateinit var option2: TextView
    private lateinit var option3: TextView
    private lateinit var option4: TextView
    private lateinit var submitButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var questionCounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitConfirmationDialog()
            }
        })

        val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Guest")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI components
        initializeViews()

        // Get category and load questions
        val categoryFromIntent = intent.getStringExtra("category") ?: "Science"
        categoryTitle.text = categoryFromIntent
        userName.text = "Player: $username"

        // Load questions based on category
        loadQuestionsByCategory(categoryFromIntent)

        // Display first question if questions are loaded
        if (questions.isNotEmpty()) {
            displayCurrentQuestion()
            setupClickListeners()
        } else {
            showError("No questions found for this category!")
        }
    }

    private fun initializeViews() {
        categoryTitle = findViewById(R.id.category_title)
        userName = findViewById(R.id.userName)
        questionText = findViewById(R.id.tv_question)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        submitButton = findViewById(R.id.btn_submit)
        progressBar = findViewById(R.id.progress_bar)
        questionCounter = findViewById(R.id.tv_question_counter)
    }

    private fun loadQuestionsByCategory(category: String) {
        questions = try {
            when (category) {
                "General-Knowledge" -> GeneralKnowledge.questions()
                "Physics" -> Physics.questions()
                "Chemistry" -> Chemistry.questions()
                "Biology" -> Biology.questions()
                "Mathematics" -> Maths.questions()
                "Engineering" -> Engineering.questions()
                "Computer Science" -> ComputerScience.questions()
                "Geo-Politics" -> geoPolitics.questions()
                "Sports" -> Sports.questions()
                "Software & Tech" -> Software.questions()
                "History" -> history.questions()
                "IQ" -> IQ.questions()
                else -> {
                    Log.e("QuizStart", "Category not found: $category")
                    emptyList()
                }
            }
        } catch (e: Exception) {
            Log.e("QuizStart", "Error loading questions for category: $category", e)
            showError("Error loading questions for category: $category")
            emptyList()
        }
    }

    private fun displayCurrentQuestion() {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]

            // Update question text
            questionText.text = currentQuestion.Question

            // Set options
            option1.text = "1. ${currentQuestion.option1}"
            option2.text = "2. ${currentQuestion.option2}"
            option3.text = "3. ${currentQuestion.option3}"
            option4.text = "4. ${currentQuestion.option4}"

            // Clear previous selection
            clearOptionSelection()
            selectedAnswer = 0

            // Update progress
            updateProgress()

            // Reset submit button
            submitButton.isEnabled = false
            submitButton.text = "Submit Answer"

        } else {
            // Quiz completed
            showQuizResults()
        }
    }

    private fun setupClickListeners() {
        // Option click listeners
        option1.setOnClickListener { selectOption(1, option1) }
        option2.setOnClickListener { selectOption(2, option2) }
        option3.setOnClickListener { selectOption(3, option3) }
        option4.setOnClickListener { selectOption(4, option4) }

        // Submit button click listener
        submitButton.setOnClickListener {
            if (selectedAnswer != 0) {
                if (submitButton.text == "Submit Answer") {
                    checkAnswer()
                } else {
                    // Next question
                    moveToNextQuestion()
                }
            }
        }
    }

    private fun selectOption(optionNumber: Int, optionView: TextView) {
        // Clear previous selection
        clearOptionSelection()

        // Set new selection
        selectedAnswer = optionNumber
        optionView.setBackgroundColor(ContextCompat.getColor(this, R.color.selected_option))
        optionView.setTextColor(ContextCompat.getColor(this, R.color.selected_text))

        // Enable submit button
        submitButton.isEnabled = true
    }

    private fun clearOptionSelection() {
        val defaultColor = ContextCompat.getColor(this, android.R.color.transparent)
        val defaultTextColor = ContextCompat.getColor(this, R.color.black)

        option1.setBackgroundColor(defaultColor)
        option2.setBackgroundColor(defaultColor)
        option3.setBackgroundColor(defaultColor)
        option4.setBackgroundColor(defaultColor)

        option1.setTextColor(defaultTextColor)
        option2.setTextColor(defaultTextColor)
        option3.setTextColor(defaultTextColor)
        option4.setTextColor(defaultTextColor)
    }

    private fun checkAnswer() {
        val currentQuestion = questions[currentQuestionIndex]
        val isCorrect = selectedAnswer == currentQuestion.correctAnswer

        // Disable all options
        disableOptions()

        // Highlight correct answer in green
        highlightCorrectAnswer(currentQuestion.correctAnswer)

        if (isCorrect) {
            score++
            Toast.makeText(this, "Correct! ✅", Toast.LENGTH_SHORT).show()
        } else {
            // Highlight wrong answer in red
            highlightWrongAnswer(selectedAnswer)
            Toast.makeText(this, "Wrong! ❌", Toast.LENGTH_SHORT).show()
        }

        // Change submit button to next button
        if (currentQuestionIndex < questions.size - 1) {
            submitButton.text = "Next Question"
        } else {
            submitButton.text = "View Results"
        }
        submitButton.isEnabled = true
    }

    private fun highlightCorrectAnswer(correctAnswer: Int) {
        val correctOption = when (correctAnswer) {
            1 -> option1
            2 -> option2
            3 -> option3
            4 -> option4
            else -> null
        }

        correctOption?.let {
            it.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_green))
            it.setTextColor(Color.WHITE)
        }
    }

    private fun highlightWrongAnswer(wrongAnswer: Int) {
        val wrongOption = when (wrongAnswer) {
            1 -> option1
            2 -> option2
            3 -> option3
            4 -> option4
            else -> null
        }

        wrongOption?.let {
            it.setBackgroundColor(ContextCompat.getColor(this, R.color.wrong_red))
            it.setTextColor(Color.WHITE)
        }
    }

    private fun disableOptions() {
        option1.isClickable = false
        option2.isClickable = false
        option3.isClickable = false
        option4.isClickable = false
    }

    private fun enableOptions() {
        option1.isClickable = true
        option2.isClickable = true
        option3.isClickable = true
        option4.isClickable = true
    }

    private fun moveToNextQuestion() {
        currentQuestionIndex++

        if (currentQuestionIndex < questions.size) {
            // Re-enable options for next question
            enableOptions()
            displayCurrentQuestion()
        } else {
            // Quiz completed
            showQuizResults()
        }
    }

    private fun updateProgress() {
        val totalQuestions = questions.size
        val progress = currentQuestionIndex + 1

        progressBar.max = totalQuestions
        progressBar.progress = progress

        questionCounter.text = "$progress / $totalQuestions"
    }

    private fun showQuizResults() {
        val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Guest")
        val totalQuestions = questions.size
        val percentage = (score * 100) / totalQuestions
        val resultMessage = "Congrats $username! \n\n" +
                "The Quiz is completed! \n\n" +
                "Final Score: $score / $totalQuestions\n" +
                "Percentage: $percentage%\n\n" +
                getPerformanceMessage(percentage)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quiz Results")
        builder.setMessage(resultMessage)
        builder.setPositiveButton("Play Again") { _, _ ->
            restartQuiz()
        }
        builder.setNegativeButton("Main Menu") { _, _ ->
            goToMainMenu()
        }
        builder.setCancelable(false)
        builder.show()

        // Save score to SharedPreferences
        saveScore(percentage, totalQuestions)
    }

    private fun getPerformanceMessage(percentage: Int): String {
        return when {
            percentage >= 90 -> "🏆 Excellent! Outstanding performance!"
            percentage >= 80 -> "🥇 Great job! Very good performance!"
            percentage >= 70 -> "🥈 Good work! Nice performance!"
            percentage >= 60 -> "🥉 Not bad! You can do better!"
            else -> "📚 Keep studying! Better luck next time!"
        }
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        selectedAnswer = 0
        enableOptions()
        displayCurrentQuestion()
    }

    private fun goToMainMenu() {
        finish() // This will close the current activity and return to the previous one
        intent
    }

    private fun saveScore(percentage: Int, totalQuestions: Int) {
        val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Guest")
        val category = categoryTitle.text.toString()

        with(sharedPref.edit()) {
            putInt("last_score_$category", score)
            putInt("last_total_$category", totalQuestions)
            putInt("last_percentage_$category", percentage)
            putString("last_quiz_user", username)
            putLong("last_quiz_time", System.currentTimeMillis())
            apply()
        }

        Log.d(
            "QuizStart",
            "Score saved: $score/$totalQuestions ($percentage%) for category: $category"
        )
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.e("QuizStart", message)
        // Optionally, you can finish the activity or redirect to main menu
        finish()
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Exit Quiz?")
            .setMessage("Are you sure you want to exit? Your progress will be lost.")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("No", null)
            .show()
    }

}

