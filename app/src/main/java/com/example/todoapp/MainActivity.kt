package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var items: ArrayList<ToDoItem>
    private lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        items = arrayListOf()
         rvAdapter = RVAdapter(items)
        binding.rvItems.adapter = rvAdapter
        binding.rvItems.layoutManager = LinearLayoutManager(this)



        binding.fabAdd.setOnClickListener{
            customDialog()
rvAdapter.notifyDataSetChanged()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemDeleted = 0

        for(i in items){
            if(i.checked){itemDeleted++}
        }

        if (itemDeleted>0){
            Toast.makeText(this, "$itemDeleted items deleted",Toast.LENGTH_LONG).show()

        }else {
            Toast.makeText(this, "No items selected",Toast.LENGTH_LONG).show()

        }

        rvAdapter.deleteItems()
        return super.onOptionsItemSelected(item)
    }
    private fun customDialog() {
        val dialogBuilder = AlertDialog.Builder(this)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val toDo = EditText(this)
        toDo.hint = "Enter to-do item"
        layout.addView(toDo)

        dialogBuilder.setPositiveButton("ADD", DialogInterface.OnClickListener{
            dialog, id -> items.add(ToDoItem(toDo.text.toString()))

        })

            .setNegativeButton("Cancel", DialogInterface.OnClickListener{

                dialog, id -> dialog.cancel()

            })
        val alert = dialogBuilder.create()
        alert.setTitle("New Item")
        alert.setView(layout)
        alert.show()

    }
}