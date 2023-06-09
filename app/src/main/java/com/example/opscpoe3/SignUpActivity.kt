// SignUpActivity.kt
package com.example.opscpoe3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SignUpActivity : AppCompatActivity() {
    private lateinit var edtFullName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtMinGoal: EditText
    private lateinit var edtMaxGoal: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnLogin: Button


    //Adapted from: TutorialsPoint Kotlin Arrays
    private val users: ArrayList<Users> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize views
        edtFullName = findViewById(R.id.edtSignUpFullNames)
        edtEmail = findViewById(R.id.edtSignUpEmail)
        edtUsername = findViewById(R.id.edtSignUpUsername)
        edtPassword = findViewById(R.id.edtSignUpPassword)
        edtMinGoal = findViewById(R.id.edtSignUpMinGoal)
        edtMaxGoal = findViewById(R.id.edtSignUpMaxGoal)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnLogin = findViewById(R.id.btnSignUpLogin)

        btnSignUp.setOnClickListener { signUpUser() }
        btnLogin.setOnClickListener { redirectToMainActivity() }
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

    private fun redirectToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Close the SignUpActivity to prevent going back to it from MainActivity
    }
}
//Adapted from : https://www.tutorialspoint.com/kotlin/kotlin_arrays.htm // TutorialsPoint // 2023
