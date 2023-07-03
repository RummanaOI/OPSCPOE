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
import com.google.android.material.bottomnavigation.BottomNavigationView

class CreateCategoryActivity : AppCompatActivity() {
    //Adapted from: Medium FindByView
    private val categories: MutableList<Category> = mutableListOf()
    private lateinit var binding: ActivityCreateCategoryBinding
    private lateinit var selectedColor: ColorObject
    private lateinit var edtCategoryName: EditText
    private lateinit var edtCategoryDescription: EditText
    private lateinit var btnSelectColor: Button
    private lateinit var btnSaveCategory: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding =
            ActivityCreateCategoryBinding.inflate(layoutInflater)//(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)
        setContentView(binding.root)//(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)
        loadColorSpinner()//(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)

        edtCategoryName = findViewById(R.id.edtAddCategoryName)
        edtCategoryDescription = findViewById(R.id.edtAddCategoryDescription)
        btnSaveCategory = findViewById(R.id.btnAddCategorySaveCategory)

        btnSaveCategory.setOnClickListener {
            val categoryName = edtCategoryName.text.toString().trim()
            val categoryDescription = edtCategoryDescription.text.toString().trim()

            if (categoryName.isNotEmpty() && categoryDescription.isNotEmpty()) {
                val category = Category(name = categoryName, description = categoryDescription)
                createCategory(category)
            } else {
                Toast.makeText(this, "Please enter a category name and description", Toast.LENGTH_SHORT).show()
            }
        }


        // Add click listener to the "Tasks" button in order to navigate to tasks page
        val btnCancel: Button = findViewById(R.id.btnAddCategoryCancel)
        btnCancel.setOnClickListener {
            navigateToCategory()
        }

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

    private fun navigateToCategory() {
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
    }

    private fun loadColorSpinner() {
        selectedColor = ColorList().defaultColor
        binding.AddCategoryColorSpinner.apply {
            adapter = ColorSpinnerAdapter(applicationContext, ColorList().basicColors())
            setSelection(ColorList().colorPosition(selectedColor), false)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedColor = ColorList().basicColors()[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }
    }

    private fun createCategory(category: Category) {
        categories.add(category)
        Toast.makeText(this, "Category created successfully", Toast.LENGTH_SHORT).show()
        clearCategoryFields()
        finish()
    }

    private fun clearCategoryFields() {
        edtCategoryName.text.clear()
        edtCategoryDescription.text.clear()
    }

    private fun getCategories(function: () -> Unit): List<Category> {
        // Return a copy of the categories list
        return categories.toList()
    }

    private fun readCategoriesFromArray() {
        val categories: List<Category> = getCategories() {
            for (category in categories) {
                // Access category properties
                val categoryName = category.name
                val categoryDescription = category.description
                //TODO: /Do something with the category data (e.g., display it in a TextView)
            }
        }
    }

}

//Color Picker Android Studio Kotlin Custom Spinner Tutorial. (2021, October 8). [Video]. Youtube. Retrieved June 6, 2023, from https://www.youtube.com/watch?v=YsKjl8ZbM4g
//Adapted from : https://medium.com/android-ideas/findviewbyid-in-kotlin-ce4d22193c79// Medium // 2019