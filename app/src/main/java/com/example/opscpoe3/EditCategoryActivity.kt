package com.example.opscpoe3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.opscpoe3.databinding.ActivityCreateCategoryBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class EditCategoryActivity : AppCompatActivity() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var binding : ActivityCreateCategoryBinding
    private lateinit var selectedColor : ColorObject
    private lateinit var edtCategoryName: EditText
    private lateinit var edtCategoryDescription: EditText
    private lateinit var btnSelectColor: Button
    private lateinit var btnSaveCategory: Button
    private lateinit var spnCategory : Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCategoryBinding.inflate(layoutInflater)//(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)
        setContentView(binding.root)//(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)
        loadColorSpinner()//(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)

        edtCategoryName = findViewById(R.id.edtEditCategoryName)
        edtCategoryDescription = findViewById(R.id.edtEditCategoryDescription)
        btnSaveCategory = findViewById(R.id.btnEditCategorySaveCategory)
        spnCategory = findViewById(R.id.spnEditTaskCategory)

        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryViewModel.populateCategories(this)

        //Populate spinner with categories
        val categories = categoryViewModel.getCategoryNamess() // replace with your categories
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCategory.adapter = adapter

        // Add click listener to the "save" button in order to save category information
        btnSaveCategory.setOnClickListener {
            saveCategory()
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
        val categoryName = edtCategoryName.text.toString()
        val categoryDescription = edtCategoryDescription.text.toString()
        val categorySelected = spnCategory.isSelected().toString()
        val idSelected = categoryViewModel.getCategoryID(categorySelected)

        // Perform validation checks here if needed

        categoryViewModel.updateCategory(this, idSelected, categoryName, categoryDescription)

        // Perform validation checks here if needed

        // Save the category to the database or perform any other desired action
        // Include the selectedColor variable when saving the category

        Toast.makeText(this, "Category saved!", Toast.LENGTH_SHORT).show()
        finish()
    }

}