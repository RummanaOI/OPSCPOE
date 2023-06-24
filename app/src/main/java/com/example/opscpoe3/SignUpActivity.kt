// SignUpActivity.kt
package com.example.opscpoe3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SignUpActivity : AppCompatActivity() {
    private lateinit var fullNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var minGoalEditText: EditText
    private lateinit var maxGoalEditText: EditText

    //Adapted from: TutorialsPoint Kotlin Arrays
    private val users: ArrayList<Users> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize views
        fullNameEditText = findViewById(R.id.edtSignUpFullNames)
        emailEditText = findViewById(R.id.edtSignUpEmail)
        usernameEditText = findViewById(R.id.edtSignUpUsername)
        passwordEditText = findViewById(R.id.edtSignUpPassword)
        minGoalEditText = findViewById(R.id.edtSignUpMinGoal)
        maxGoalEditText = findViewById(R.id.edtSignUpMaxGoal)

        val signUpButton = findViewById<Button>(R.id.btnSignUp)
        val loginButton = findViewById<Button>(R.id.btnSignUpLogin)

        signUpButton.setOnClickListener { signUpUser() }
        loginButton.setOnClickListener { redirectToMainActivity() }
    }

    private fun signUpUser() {
        val fullName = fullNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()
        val minGoal = minGoalEditText.text.toString()
        val maxGoal = maxGoalEditText.text.toString()

        // Validate user data
        if (isValidRegistration(fullName, email, username, password, minGoal, maxGoal)) {
            // Create a new user
            val newUser = Users(fullName, email, username, password, minGoal, maxGoal)

            // Add the new user to the list
            users.add(newUser)

            Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()

            // Clear input fields
            fullNameEditText.text.clear()
            emailEditText.text.clear()
            usernameEditText.text.clear()
            passwordEditText.text.clear()
            minGoalEditText.text.clear()
            maxGoalEditText.text.clear()
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

    private fun redirectToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Close the SignUpActivity to prevent going back to it from MainActivity
    }
}
//Adapted from : https://www.tutorialspoint.com/kotlin/kotlin_arrays.htm // TutorialsPoint // 2023
