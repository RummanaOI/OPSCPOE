package com.example.opscpoe3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class CreateTaskActivity : AppCompatActivity() {

    private val tasks: MutableList<Task> = mutableListOf()

    private lateinit var edtTaskName: EditText
    private lateinit var edtDescription: EditText
    private lateinit var edtHours: EditText
    private lateinit var edtCurrentDate: EditText
    private lateinit var spnCategory : Spinner
    private lateinit var btnAddTask: Button
    private lateinit var btnCancel :Button
    private lateinit var btnAddTaskPhoto :Button
    private lateinit var imgTaskPhoto :ImageView
    private val PHOTO_PICKER_REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        // Initialize views
        edtTaskName = findViewById(R.id.edtAddTaskName)
        edtDescription = findViewById(R.id.edtAddTaskDescription)
        edtHours = findViewById(R.id.edtAddTaskTime)
        btnAddTask = findViewById(R.id.btnAddTask)
        btnCancel = findViewById(R.id.btnAddTaskCancel)
        btnAddTaskPhoto = findViewById(R.id.btnAddTaskPhoto)
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
            val hours = edtHours.text.toString()
            val currentDate = edtCurrentDate.text.toString()
            val category = spnCategory.selectedItem.toString() // Get selected category from Spinner
            // TODO: Save the task details to the database or perform desired action

            finish() // Close the activity and return to the previous screen
        }


        btnAddTask.setOnClickListener {
            if (validateTaskFields()) {
                val task = Task(
                    category = spnCategory.selectedItem.toString(),
                    name = edtTaskName.text.toString(),
                    description = edtDescription.text.toString(),
                    date = edtCurrentDate.text.toString(),
                    hours = edtHours.text.toString().toInt(),
                    imageUrl = "https://example.com/image.jpg"
                )
                createTask(task)
            }
        }

        // Add click listener to the "Tasks" button in order to navigate to tasks page
        val btnCancel: Button = findViewById(R.id.btnAddTaskCancel)
        btnCancel.setOnClickListener {
            navigateToTasks()
        }

        btnAddTaskPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PHOTO_PICKER_REQUEST_CODE)
        }

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == PHOTO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
                val photoUri = data?.data
                imgTaskPhoto.setImageURI(photoUri)
                // Save the URI somewhere if you need it later
            }
        }

        //Functionality for navbar
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_tasks -> {
                    // Navigate to Tasks
                    val intent = Intent(this, TaskActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_categories -> {
                    // Navigate to Categories
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_statistics -> {
                    // Navigate to Statistics
                    val intent = Intent(this, StatisticsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_rewards -> {
                    // Navigate to Rewards
                    val intent = Intent(this, RewardsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_settings -> {
                    // Navigate to Settings
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
    private fun navigateToTasks() {
        val intent = Intent(this, TaskActivity::class.java)
        startActivity(intent)
    }

    private fun createTask(task: Task) {
        tasks.add(task)
        Toast.makeText(this, "Task created successfully", Toast.LENGTH_SHORT).show()
        clearTaskFields()
    }

    private fun clearTaskFields() {
        edtTaskName.text.clear()
        edtDescription.text.clear()
        edtHours.text.clear()
        edtCurrentDate.text.clear()
    }

    private fun updateTask(task: Task) {
        // Find the index of the task in the tasks list
        val index = tasks.indexOfFirst { it.name == task.name }

        if (index != -1) {
            tasks[index] = task
            Toast.makeText(this, "Task updated successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getTasks(): List<Task> {
        // Return a copy of the tasks list
        return tasks.toList()
    }

    private fun readTasksFromArray() {
        val tasks: List<Task> = getTasks()
        for (task in tasks) {
            // Access task properties
            val taskName = task.name
            val description = task.description
            val hours = task.hours
            val currentDate = task.date
            val category = task.category

            //  Do something with the task data (e.g., display it in a TextView)
        }
    }
    private fun validateTaskFields(): Boolean {
        val taskName = edtTaskName.text.toString().trim()
        val description = edtDescription.text.toString().trim()
        val hours = edtHours.text.toString().trim()
        val currentDate = edtCurrentDate.text.toString().trim()

        // Validate that all required fields are not empty
        if (taskName.isEmpty()) {
            edtTaskName.error = "Task name is required"
            edtTaskName.requestFocus()
            return false
        }

        if (description.isEmpty()) {
            edtDescription.error = "Description is required"
            edtDescription.requestFocus()
            return false
        }

        if (hours.isEmpty()) {
            edtHours.error = "Hours is required"
            edtHours.requestFocus()
            return false
        }

        if (currentDate.isEmpty()) {
            edtCurrentDate.error = "Current date is required"
            edtCurrentDate.requestFocus()
            return false
        }

        return true
    }
}



