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
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miDelete ) {
            var itemsDeleted = 0
            for(i in myList){
                if(i.checked){itemsDeleted++}
            }

            if(itemsDeleted > 0){
                Toast.makeText(this, "$itemsDeleted items deleted", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "No items selected", Toast.LENGTH_LONG).show()
            }
            rvAdapter.deleteItems()
            RV.adapter?.notifyDataSetChanged()
            return true
        }
        return super.onOptionsItemSelected(item)
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
