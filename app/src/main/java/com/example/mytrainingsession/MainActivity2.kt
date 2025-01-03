package com.example.mytrainingsession

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity2 : AppCompatActivity() {

    private val exercises = ExerciseDataBase.exercises

    private lateinit var toolbarTB:Toolbar

    private lateinit var titleTW: TextView
    private lateinit var exerciseTV: TextView
    private lateinit var descriptionTV: TextView
    private lateinit var timerTV: TextView

    private lateinit var startButtonBTN: Button
    private lateinit var backButtonBTN: Button

    private lateinit var imageViewIV: ImageView

    private lateinit var currentExercise: Exercise
    private lateinit var timer: CountDownTimer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)

        init()

        startButtonBTN.setOnClickListener {
            startWorkout()
        }
        backButtonBTN.setOnClickListener {
            completedExercise()
        }
    }

    private fun init() {
        titleTW = findViewById(R.id.titleTW)
        exerciseTV = findViewById(R.id.exerciseTV)
        descriptionTV = findViewById(R.id.descriptionTV)
        startButtonBTN = findViewById(R.id.startButtonBTN)
        backButtonBTN = findViewById(R.id.backButtonBTN)
        timerTV = findViewById(R.id.timerTV)
        imageViewIV = findViewById(R.id.imageViewIV)
    }

    private fun completedExercise() {
        timer.cancel()
        val intent = Intent(this,MainActivity3::class.java)
        startActivity(intent)
        finish()
    }

    private fun startWorkout() {
        titleTW.text = "Начало тренировки"
        startButtonBTN.isEnabled = false
        startButtonBTN.text = "Процесс тренировки"
        startNextExercise()
    }

    private fun startNextExercise() {
        val x = intent.extras?.getInt("position")
            currentExercise = exercises[x?:0]
            exerciseTV.text = currentExercise.name
            descriptionTV.text = currentExercise.description
            imageViewIV.setImageResource(exercises[x?:0].gifImage)
            timerTV.text = formatTime(currentExercise.durationInSeconds)
            timer = object : CountDownTimer(
                currentExercise.durationInSeconds * 1000L,
                1000
            ){
                override fun onTick(p0: Long) {
                    timerTV.text = formatTime((p0/1000).toInt())
                }

                override fun onFinish() {
                    timerTV.text = "Упражнение завершено"
                    imageViewIV.visibility = View.VISIBLE
                    backButtonBTN.isEnabled = true
                    descriptionTV.text = ""
                    imageViewIV.setImageResource(0)
                    startButtonBTN.setText("Повторить")
                    startButtonBTN.isEnabled = true
                }
            }.start()
    }

    @SuppressLint("DefaultLocale")
    private fun formatTime(seconds: Int): String {
        val minutes = seconds/60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d",minutes,remainingSeconds)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.exitItem -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}