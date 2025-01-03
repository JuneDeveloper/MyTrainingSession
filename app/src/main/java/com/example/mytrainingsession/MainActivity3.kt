package com.example.mytrainingsession

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity3 : AppCompatActivity() {

    private lateinit var toolbarTB:Toolbar
    private lateinit var listViewLV:ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)

        listViewLV = findViewById(R.id.listViewLV)

        val adapter = arrayAdapter()

        listViewLV.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val z = adapter.getItemId(i)
                val intent = Intent(this,MainActivity2::class.java)
                intent.putExtra("position",z.toInt())
                startActivity(intent)
            }
    }

    private fun arrayAdapter(): ArrayAdapter<String> {
        val exercise = mutableListOf<String>()
        val x = ExerciseDataBase.exercises
        for (i in x.map { it.name }) exercise.add(i)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, exercise)
        listViewLV.adapter = adapter
        return adapter
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