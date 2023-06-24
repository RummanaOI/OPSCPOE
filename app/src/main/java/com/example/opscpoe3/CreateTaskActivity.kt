package com.example.opscpoe3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.EditText

class CreateTaskActivity : AppCompatActivity() {

    private lateinit var editTextTaskName: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var editTextStartTime: EditText
    private lateinit var editTextEndTime: EditText
    private lateinit var buttonAddTask: Button
    private lateinit var buttonCancel :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        // Initialize views
        editTextTaskName = findViewById(R.id.edtAddTaskName)
        editTextDescription = findViewById(R.id.edtAddTaskDescription)
        editTextStartTime = findViewById(R.id.edtAddTaskStartTime)
        editTextEndTime = findViewById(R.id.edtAddTaskEndTime)
        buttonAddTask = findViewById(R.id.btnAddTask)
        buttonCancel = findViewById(R.id.btnAddTaskCancel)

        // Add task button click listener
        buttonAddTask.setOnClickListener {
            val taskName = editTextTaskName.text.toString()
            val description = editTextDescription.text.toString()
            val startTime = editTextStartTime.text.toString()
            val endTime = editTextEndTime.text.toString()

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


