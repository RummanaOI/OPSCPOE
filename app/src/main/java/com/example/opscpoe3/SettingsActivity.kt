package com.example.opscpoe3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsActivity : AppCompatActivity() {

    private lateinit var edtFullName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtMinGoal: EditText
    private lateinit var edtMaxGoal: EditText
    private lateinit var chkNotifications: CheckBox
    private lateinit var btnSave : Button

    private val users: ArrayList<Users> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize views
        edtFullName = findViewById(R.id.edtSettingsFullNames)
        edtEmail = findViewById(R.id.edtSettingsEmail)
        edtUsername = findViewById(R.id.edtSettingsUsername)
        edtPassword = findViewById(R.id.edtSettingsPassword)
        edtMinGoal = findViewById(R.id.edtSettingsMinGoal)
        edtMaxGoal = findViewById(R.id.edtSettingsMaxGoal)
        chkNotifications = findViewById(R.id.chkSettingsNotifications)
        btnSave = findViewById(R.id.btnSettingsSave)

        btnSave.setOnClickListener { signUpUser() }

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


    private fun signUpUser() {
        val fullName = edtFullName.text.toString()
        val email = edtEmail.text.toString()
        val username = edtUsername.text.toString()
        val password = edtPassword.text.toString()
        val minGoal = edtMinGoal.text.toString()
        val maxGoal = edtMaxGoal.text.toString()

        // Validate user data
        if (isValidRegistration(fullName, email, username, password, minGoal, maxGoal)) {
            // Create a new user
            val newUser = Users(fullName, email, username, password, minGoal, maxGoal)

            // Add the new user to the list
            users.add(newUser)

            Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()

            // Clear input fields
            edtFullName.text.clear()
            edtEmail.text.clear()
            edtUsername.text.clear()
            edtPassword.text.clear()
            edtMinGoal.text.clear()
            edtMaxGoal.text.clear()
        } else {
            Toast.makeText(this, "Invalid user data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidRegistration(
        fullName: String,
        email: String,
        username: String,
        password: String,
        minGoal: String,
        maxGoal: String
    ): Boolean {
        // Check if the email or username is already taken
        val existingUser = users.find { it.email == email || it.username == username }
        return existingUser == null &&
                fullName.isNotEmpty() &&
                email.isNotEmpty() &&
                username.isNotEmpty() &&
                password.isNotEmpty() &&
                minGoal.isNotEmpty() &&
                maxGoal.isNotEmpty()
    }
}