package com.example.opscpoe3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.content.Intent
import android.graphics.Color
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class CategoryActivity : AppCompatActivity() {

    private lateinit var llCategoryList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // Get references to layout components
        llCategoryList = findViewById(R.id.llCategoryList)

        // Add click listener to the "Add Category" button to navigate to add category page
        val btnAddCategory: Button = findViewById(R.id.btnAddCategory)
        btnAddCategory.setOnClickListener {
            navigateToCreateCategory()
        }

        // Fetch category data from the database and populate the category list
        fetchCategoriesFromDatabase()

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

        private fun navigateToCreateCategory() {
            val intent = Intent(this, CreateCategoryActivity::class.java)
            startActivity(intent)
        }

    private fun fetchCategoriesFromDatabase() {
        // Fetch category data from the database (replace with your implementation)

        // Dummy category data for demonstration purposes

        val blue = ContextCompat.getColor(this, R.color.blue_category1)
        val red = ContextCompat.getColor(this, R.color.red_category3)
        val green = ContextCompat.getColor(this, R.color.green_category2)
        val categories = listOf(
            Category("Work", "Manage work-related tasks", red),
            Category("Personal", "Organize personal activities", blue),
            Category("Study", "Track study sessions", green)
        )

        // Iterate over the categories and create category items dynamically
        for (category in categories) {
            val categoryItemView = createCategoryItemView(category)
            llCategoryList.addView(categoryItemView)
        }
    }

    private fun createCategoryItemView(category: Category): View {
        // Inflate the category item layout
        //Adapted from: JavaTPoint TextView and EditView
        val categoryItemView =
            LayoutInflater.from(this).inflate(R.layout.category_item, null) as LinearLayout

        // Set background color of the category block
        categoryItemView.setBackgroundColor(category.color)

        val textColour = ContextCompat.getColor(this, R.color.dark_brown_text)
        // Set category name and description
        val txtCategoryName: TextView = categoryItemView.findViewById(R.id.txtCategoryName)
        txtCategoryName.text = category.name
        txtCategoryName.setTextColor(textColour)

        val txtCategoryDescription: TextView =
            categoryItemView.findViewById(R.id.txtCategoryDescription)
        txtCategoryDescription.text = category.description
        txtCategoryDescription.setTextColor(textColour)

        // Set click listener for the category item
        categoryItemView.setOnClickListener {
            // Handle category item click event (e.g., navigate to category details)
            // You can customize this logic based on your application requirements
        }

        return categoryItemView
    }

}//Adapted from : https://www.javatpoint.com/kotlin-android-textview-and-edittext// JavaTPoint // 2019