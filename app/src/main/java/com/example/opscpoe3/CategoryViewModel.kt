package com.example.opscpoe3

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File

// Assuming User is a data class with an id property
// data class User(val id: String, val name: String, val email: String, val username: String, val password: String, val minGoal: Int, val maxGoal: Int)

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val categories = mutableListOf<Category>()
    private var categoryCount = 0
    private val exampleCategory = Category(1, "Work", "My job")

    fun populateCategories(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val file = File(context.filesDir, "CategoryList.txt")
            if (!file.exists()) {
                file.createNewFile()
                categoryCount = 1
                val defaultCategory = "1#Work#My job"
                file.writeText(defaultCategory)
                categories.add(exampleCategory)
            } else {
                file.readLines().forEach { line ->
                    // Parsing each line into a User
                    val categoryData = line.split("#")
                    val category = Category(
                        id = categoryData[0].toInt(),
                        name = categoryData[1],
                        description = categoryData[2]
                    )
                    categories.add(category)
                    categoryCount=+1
                }
            }
        }
    }

    fun getCategory(id:Int): Category {
        var Category = exampleCategory
        for (category in categories) {
            if (category.id == id) {
                Category = category
            }
        }
        return Category
    }

    fun getCategoryID(name: String): Int {
        var Category = exampleCategory
        for (category in categories) {
            if (category.name == name) {
                Category = category
            }
        }
        return Category.id
    }

    fun addCategory(context: Context, name: String, description : String) {
        // Create a new user
        val addCategoryID = categoryCount + 1
        val newCategory = Category(addCategoryID, name, description)

        // Add the new user to the users list
        categories.add(newCategory)
        categoryCount=+1

        // Write the new user's information to the UserList.txt file
        val file = File(context.filesDir, "CategoryList.txt")
        file.appendText("\n${newCategory.id}#${newCategory.name}#${newCategory.description}")
    }


    fun updateCategory(context: Context, id:Int, name: String, description: String) {

        var category = getCategory(id)

        // Update the user details
        category.let {
            it.name = name
            it.description = description
        }

        // Update the user details in the text file
        val file = File(context.filesDir, "CategoryList.txt")
        val lines = file.readLines().toMutableList()

        // Find the line with the current user's details
        val index = lines.indexOfFirst { it.startsWith("${category.id}#") }

        // Replace the line with the updated user details
        if (index != -1) {
            lines[index] = "${category.id}#$name#$description"
            file.writeText(lines.joinToString("\n"))
        }
    }

    fun getCategories(): List<Category> {
        return categories
    }
    fun getCategoryIDs(): List<Int> {
        val ids = mutableListOf<Int>()
        for (category in categories){
            ids.add(category.id)
        }
        return ids
    }
    fun getCategoryNamess(): List<String> {
        val names = mutableListOf<String>()
        for (category in categories){
            names.add(category.name)
        }
        return names
    }

}