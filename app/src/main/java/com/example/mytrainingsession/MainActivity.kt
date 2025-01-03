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
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.zip.Inflater
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarTB:androidx.appcompat.widget.Toolbar
    private lateinit var startImageIV:ImageView
    private lateinit var startTextTV:TextView
    private lateinit var startButtonBTN:Button

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)

        startImageIV = findViewById(R.id.startImageIV)
        startTextTV = findViewById(R.id.startTextTV)
        startButtonBTN = findViewById(R.id.startButtonBTN)

        startImageIV.setImageResource(R.drawable.ixbtmedia)
        startTextTV.setText("Будь в форме круглый год,\n а не только летом!:)")
        startButtonBTN.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
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