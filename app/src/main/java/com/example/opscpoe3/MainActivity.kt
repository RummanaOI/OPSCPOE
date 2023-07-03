package com.example.opscpoe3

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    // Sample user data array
    //Adapted from: JavaTPoint  Android Activity Page
    private lateinit var auth: FirebaseAuth
    private lateinit var txtEmailAddress: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnHomeNoAccount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        txtEmailAddress = findViewById(R.id.txtHomeEmail)
        txtPassword = findViewById(R.id.txtHomePassword)
        btnLogin = findViewById(R.id.btnHomeLogin)
        btnHomeNoAccount = findViewById(R.id.btnHomeNoAccount)
        btnLogin.setOnClickListener { performLogin() }
        btnHomeNoAccount.setOnClickListener{signUpUser()}
    }
    private fun signUpUser(){
        val intent = Intent(this,SignUpActivity ::class.java)
        startActivity(intent)
    }


    private fun performLogin() {
        //gets input from the user
        val email = txtEmailAddress.text.toString()
        val password = txtPassword.text.toString()

        //check if null
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill in credentials",Toast.LENGTH_SHORT)
                .show()
            return
            //if not null then try login
        }else{
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful) {
                        //Sign in success
                        //change intents where necessary
                        val intent = Intent(this, TaskActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(baseContext, "Successful", Toast.LENGTH_SHORT).show()
                    } else {
                        //if sign in fails
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

}
//Adapted from : https://www.javatpoint.com/android-life-cycle-of-activity// JavaTPoint // 2019