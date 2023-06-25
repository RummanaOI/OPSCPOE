package com.example.opscpoe3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class settings : AppCompatActivity() {

    private lateinit var edtFullName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtMinGoal: EditText
    private lateinit var edtMaxGoal: EditText

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

    }
}