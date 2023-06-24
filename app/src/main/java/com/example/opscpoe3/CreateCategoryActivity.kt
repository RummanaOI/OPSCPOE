package com.example.opscpoe3
// CreateCategoryActivity.kt

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.opscpoe3.databinding.ActivityCreateCategoryBinding

class CreateCategoryActivity : AppCompatActivity() {
    //Adapted from: Medium FindByView
    private lateinit var binding : ActivityCreateCategoryBinding
    private lateinit var selectedColor : ColorObject
    private lateinit var edtCategoryName: EditText
    private lateinit var edtCategoryDescription: EditText
    private lateinit var btnSelectColor: Button
    private lateinit var btnSaveCategory: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCreateCategoryBinding.inflate(layoutInflater)//(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)
        setContentView(binding.root)//(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)
        loadColorSpinner()//(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)

        edtCategoryName = findViewById(R.id.edtAddCategoryName)
        edtCategoryDescription = findViewById(R.id.edtAddCategoryDescription)
        btnSaveCategory = findViewById(R.id.btnAddCategorySaveCategory)

        // Add click listener to the "save" button in order to save category information
        btnSaveCategory.setOnClickListener {
            saveCategory()
        }

        // Add click listener to the "Tasks" button in order to navigate to tasks page
        val btnCancel: Button = findViewById(R.id.btnAddCategoryCancel)
        btnCancel.setOnClickListener {
            navigateToCategory()
        }
    }

    private fun navigateToCategory() {
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
    }

    private fun loadColorSpinner()
    {
        selectedColor = ColorList().defaultColor
        binding.AddCategoryColorSpinner.apply{
            adapter = ColorSpinnerAdapter(applicationContext, ColorList().basicColors())
            setSelection(ColorList().colorPosition(selectedColor), false)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
                {
                    selectedColor = ColorList().basicColors()[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }
    }

    private fun saveCategory() {
        val categoryName = edtCategoryName.text.toString().trim()
        val categoryDescription = edtCategoryDescription.text.toString().trim()

        // Perform validation checks here if needed

        // Save the category to the database or perform any other desired action
        // Include the selectedColor variable when saving the category

        Toast.makeText(this, "Category saved!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
//Color Picker Android Studio Kotlin Custom Spinner Tutorial. (2021, October 8). [Video]. Youtube. Retrieved June 6, 2023, from https://www.youtube.com/watch?v=YsKjl8ZbM4g
//Adapted from : https://medium.com/android-ideas/findviewbyid-in-kotlin-ce4d22193c79// Medium // 2019