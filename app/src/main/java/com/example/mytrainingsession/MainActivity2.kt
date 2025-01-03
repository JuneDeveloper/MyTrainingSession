package com.example.mytrainingsession

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    private val exercises = ExerciseDataBase.exercises

    private lateinit var toolbarTB:Toolbar

    private lateinit var titleTW: TextView
    private lateinit var exerciseTV: TextView
    private lateinit var descriptionTV: TextView
    private lateinit var timerTV: TextView

    private lateinit var startButtonBTN: Button
    private lateinit var completedButtonBTN: Button

    private lateinit var imageViewIV: ImageView

    private var exerciseIndex = 0
    private lateinit var currentExercise: Exercise
    private lateinit var timer: CountDownTimer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)

        titleTW = findViewById(R.id.titleTW)
        exerciseTV = findViewById(R.id.exerciseTV)
        descriptionTV = findViewById(R.id.descriptionTV)
        startButtonBTN = findViewById(R.id.startButtonBTN)
        completedButtonBTN = findViewById(R.id.completedButtonBTN)
        timerTV = findViewById(R.id.timerTV)
        imageViewIV = findViewById(R.id.imageViewIV)

        startButtonBTN.setOnClickListener {
            startWorkout()
        }
        completedButtonBTN.setOnClickListener {
            completedExercise()
        }
    }

    private fun completedExercise() {
        timer.cancel()
        completedButtonBTN.isEnabled = false
        startNextExercise()
    }

    private fun startWorkout() {
        exerciseIndex = 0
        titleTW.text = "Начало тренировки"
        startButtonBTN.isEnabled = false
        startButtonBTN.text = "Процесс тренировки"
        startNextExercise()
    }

    private fun startNextExercise() {
        if(exerciseIndex < exercises.size) {
            currentExercise = exercises[exerciseIndex]
            exerciseTV.text = currentExercise.name
            descriptionTV.text = currentExercise.description
            imageViewIV.setImageResource(exercises[exerciseIndex].gifImage)
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
                    completedButtonBTN.isEnabled = true
                    imageViewIV.setImageResource(0)
                }
            }.start()
            exerciseIndex++
        }else{
            exerciseTV.text = "Тренировка завершена"
            descriptionTV.text = ""
            timerTV.text = ""
            completedButtonBTN.isEnabled = false
            startButtonBTN.isEnabled = true
            startButtonBTN.text = "Начать снова"
        }
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