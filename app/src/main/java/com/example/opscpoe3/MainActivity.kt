package com.example.opscpoe3

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Sample user data array
    //Adapted from: JavaTPoint  Android Activity Page
    private val users = arrayOf(
        User("Rummana", "password1"),
        User("Kaiyur", "password2"),
        User("Sharif", "password3")
    )

    private lateinit var txtUsername: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnHomeNoAccount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtUsername = findViewById(R.id.txtHomeUsername)
        txtPassword = findViewById(R.id.txtHomePassword)
        btnLogin = findViewById(R.id.btnHomeLogin)
        btnHomeNoAccount = findViewById(R.id.btnHomeNoAccount)
        btnLogin.setOnClickListener { loginUser() }
        btnHomeNoAccount.setOnClickListener{signUpUser()}
    }
    private fun signUpUser(){
        val intent = Intent(this,SignUpActivity ::class.java)
        startActivity(intent)
    }


    private fun loginUser() {
        val username = txtUsername.text.toString()
        val password = txtPassword.text.toString()

        val isValidUser = validateUser(username, password)

        if (isValidUser) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            // Proceed with further actions after successful login
            //directs to create category if user credentials are correct
            val intent = Intent(this,CategoryActivity ::class.java)
            startActivity(intent)


        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()

        }
    }


    private fun validateUser(username: String, password: String): Boolean {
        for (user in users) {
            if (user.username == username && user.password == password) {
                return true
            }
        }
        return false
    }

    data class User(val username: String, val password: String)
}
//Adapted from : https://www.javatpoint.com/android-life-cycle-of-activity// JavaTPoint // 2019