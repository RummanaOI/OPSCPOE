package com.example.opscpoe3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.widget.*

class CreateTaskActivity : AppCompatActivity() {

    private lateinit var edtTaskName: EditText
    private lateinit var edtDescription: EditText
    private lateinit var edtStartTime: EditText
    private lateinit var edtEndTime: EditText
    private lateinit var spnCategory : Spinner
    private lateinit var btnAddTask: Button
    private lateinit var btnCancel :Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        // Initialize views
        edtTaskName = findViewById(R.id.edtAddTaskName)
        edtDescription = findViewById(R.id.edtAddTaskDescription)
        edtStartTime = findViewById(R.id.edtAddTaskStartTime)
        edtEndTime = findViewById(R.id.edtAddTaskEndTime)
        btnAddTask = findViewById(R.id.btnAddTask)
        btnCancel = findViewById(R.id.btnAddTaskCancel)
        spnCategory = findViewById(R.id.spnAddTaskCategory)

        //Populate spinner with categories
        val categories = arrayOf("Category 1", "Category 2", "Category 3") // replace with your categories
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCategory.adapter = adapter


        // Add task button click listener
        btnAddTask.setOnClickListener {
            val taskName = edtTaskName.text.toString()
            val description = edtDescription.text.toString()
            val startTime = edtStartTime.text.toString()
            val endTime = edtEndTime.text.toString()
            val category = spnCategory.selectedItem.toString() // Get selected category from Spinner
            // TODO: Save the task details to the database or perform desired action

            finish() // Close the activity and return to the previous screen
        }

        // Add click listener to the "Tasks" button in order to navigate to tasks page
        val btnCancel: Button = findViewById(R.id.btnAddTaskCancel)
        btnCancel.setOnClickListener {
            navigateToTasks()
        }
    }
    private fun navigateToTasks() {
        val intent = Intent(this, TaskActivity::class.java)
        startActivity(intent)
    }
}


