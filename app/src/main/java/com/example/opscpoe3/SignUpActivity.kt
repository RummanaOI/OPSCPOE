// SignUpActivity.kt
package com.example.opscpoe3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

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
        //initialise firebase
        auth = Firebase.auth

        // Initialize views
        edtFullName = findViewById(R.id.edtSignUpFullNames)
        edtEmail = findViewById(R.id.edtSignUpEmail)
        edtUsername = findViewById(R.id.edtSignUpUsername)
        edtPassword = findViewById(R.id.edtSignUpPassword)
        edtMinGoal = findViewById(R.id.edtSignUpMinGoal)
        edtMaxGoal = findViewById(R.id.edtSignUpMaxGoal)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnLogin = findViewById(R.id.btnSignUpLogin)

        btnSignUp.setOnClickListener { performSignUp() }
        btnLogin.setOnClickListener { redirectToMainActivity() }
    }
    private fun performSignUp() {
        val fullName = edtFullName.text.toString()
        val email = edtEmail.text.toString()
        val username = edtUsername.text.toString()
        val password = edtPassword.text.toString()
        val minGoal = edtMinGoal.text.toString()
        val maxGoal = edtMaxGoal.text.toString()

        //check if values are null
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill in credentials", Toast.LENGTH_SHORT)
                .show()
            return
            //if not add user
        }else{
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful) {
                        //Sign in success, move to MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(baseContext, "Successful", Toast.LENGTH_SHORT).show()
                    } else {
                        //if sign in fails, messaged displayed to user
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(
                        this, "Error occurred${it.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }

        }

    }

    private fun redirectToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Close the SignUpActivity to prevent going back to it from MainActivity
    }
}
//Adapted from : https://www.tutorialspoint.com/kotlin/kotlin_arrays.htm // TutorialsPoint // 2023
