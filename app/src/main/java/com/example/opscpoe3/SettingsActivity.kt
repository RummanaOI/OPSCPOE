package com.example.opscpoe3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsActivity : AppCompatActivity() {

    private lateinit var edtFullName: EditText
    private lateinit var edtEmail: EditText
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
        val password = edtPassword.text.toString()
        val minGoal = edtMinGoal.text.toString()
        val maxGoal = edtMaxGoal.text.toString()


        //checks if fields are left empty
        if (edtEmail.text.isEmpty() || edtPassword.text.isEmpty()) {
            Toast.makeText(this, "Please fill in credentials", Toast.LENGTH_SHORT)
                .show()
            return
        } else {
            //Updates user credentials
            //get the logged in user
            val user = Firebase.auth.currentUser
            //updates email
            user!!.updateEmail(edtEmail.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "User email address updated.",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    }
                }

            //updates password
            val newPassword = edtPassword

            user!!.updatePassword(newPassword.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "User email address updated.",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
        }
    }

}