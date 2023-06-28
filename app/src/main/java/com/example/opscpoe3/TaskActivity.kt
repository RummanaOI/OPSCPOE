package com.example.opscpoe3

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Date

class TaskActivity : AppCompatActivity() {

    private lateinit var taskContainer: LinearLayout
    private lateinit var btnAddTask: Button
    private lateinit var datePickerDialog: DatePickerDialog//(Code with Cal, 2020)
    private lateinit var buttonSelectDate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        initDatePicker()//(Code with Cal, 2020)
        buttonSelectDate = findViewById(R.id.btnTaskSelectDate)//(Code with Cal, 2020)
        buttonSelectDate.text = getTodaysDate()//(Code with Cal, 2020)

        // Initialize views
        taskContainer = findViewById(R.id.taskContainer)
        btnAddTask = findViewById(R.id.btnAddTask)

        loadTasksForSelectedDate()
        // Handle Add Task button click
        btnAddTask.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            startActivity(intent)
        }

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
    //(Code with Cal, 2020)
    private fun getTodaysDate(): String {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val day = cal.get(Calendar.DAY_OF_MONTH)
        return makeDateString(day, month, year)
    }

    //(Code with Cal, 2020)
    private fun initDatePicker() {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val formattedDate = makeDateString(day, month + 1, year)
                buttonSelectDate.text = formattedDate
            }

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)


        datePickerDialog = DatePickerDialog(this, dateSetListener, year, month, day)
    }

    //(Code with Cal, 2020)
    private fun makeDateString(day: Int, month: Int, year: Int): String {
        return "${getMonthFormat(month)} $day $year"
    }

    //(Code with Cal, 2020)
    private fun getMonthFormat(month: Int): String {
        return when (month) {
            1 -> "Jan"
            2 -> "Feb"
            3 -> "Mar"
            4 -> "Apr"
            5 -> "May"
            6 -> "Jun"
            7 -> "Jul"
            8 -> "Aug"
            9 -> "Sep"
            10 -> "Oct"
            11 -> "Nov"
            12 -> "Dec"
            else -> "Jan"
        }
    }

    //(Code with Cal, 2020)
    fun openDatePicker(view: View) {
        datePickerDialog.show()
    }

    //
    private fun loadTasksForSelectedDate() {
        // Clear existing tasks from the task container
        taskContainer.removeAllViews()

        // Fetch tasks from the database for the selected date
        val tasks = fetchTasksFromDatabase()

        // Inflate task views and add them to the task container
        for (task in tasks) {
            val taskView = LayoutInflater.from(this)
                .inflate(R.layout.task_item, taskContainer, false)

            val txtTaskName: TextView = taskView.findViewById(R.id.txtTaskName)
            val txtTaskDescription: TextView = taskView.findViewById(R.id.txtTaskDescription)

            txtTaskName.text = task.name
            txtTaskDescription.text = task.description

            taskContainer.addView(taskView)
        }
    }

    private fun fetchTasksFromDatabase(): List<Task> {
        // TODO: Fetch tasks from the database for the selected date
        // Replace this with your actual implementation
        val currentDate = Date()

        return listOf(
            Task("Work", "Make presentation", "Make powerpoint presentation", "08:00 AM", "10:00 AM", null, currentDate),
            Task("Personal", "Doctor appointment", "In Menlyn", "12:00 PM", "02:00 PM", null, currentDate),
            Task("Study", "OPSC POE", "Complete part 2", "04:00 PM", "06:00 PM", null, currentDate)
        )
    }

}

data class Task(
    val category: String,
    val name: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val image: String?,
    val date: Date
)

//Code with Cal. (2020, December 20). Pop Up Date Picker Android Studio Tutorial [Video]. YouTube. Retrieved June 6, 2023, from https://www.youtube.com/watch?v=qCoidM98zNk