package com.example.to_doapp

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var RV : RecyclerView
    lateinit var fButton: FloatingActionButton
    private lateinit var rvAdapter: To_Do_Adapter
    var myList = ArrayList<taskModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fButton = findViewById(R.id.fab)
        RV = findViewById(R.id.recyclerView)
        rvAdapter = To_Do_Adapter(myList)
        RV.adapter = rvAdapter
        RV.layoutManager = LinearLayoutManager(this)

        fButton.setOnClickListener { myTasks() }



    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    fun myTasks(){
        val dialogBuilder = AlertDialog.Builder(this)
        val input = EditText(this)
        dialogBuilder.setMessage(" ")
            .setPositiveButton("Add", DialogInterface.OnClickListener {
                    dialog, id -> myList.add(taskModel(input.text.toString()))
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("New Item")
        alert.setView(input)
        alert.show()


    }
}