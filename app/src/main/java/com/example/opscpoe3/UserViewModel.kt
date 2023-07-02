package com.example.opscpoe3

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

// Assuming User is a data class with an id property
// data class User(val id: String, val name: String, val email: String, val username: String, val password: String, val minGoal: Int, val maxGoal: Int)

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val users = mutableListOf<User>()
    private var userCount = 0
    private var currentUser: User? = null

    fun populateUsers(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val file = File(context.filesDir, "UsersList.txt")
            if (!file.exists()) {
                file.createNewFile()
                userCount = 1
                val defaultUser = "0#Rummana#rummana@gmail.com#romy#Password1#2#6"
                file.writeText(defaultUser)
                val userData = defaultUser.split("#")
                currentUser = User(
                    id = userData[0].toInt(),
                    name = userData[1],
                    email = userData[2],
                    username = userData[3],
                    password = userData[4],
                    minGoal = userData[5],
                    maxGoal = userData[6]
                )
                users.add(currentUser!!)
            } else {
                file.readLines().forEach { line ->
                    // Parsing each line into a User
                    val userData = line.split("#")
                    val user = User(
                        id = userData[0].toInt(),
                        name = userData[1],
                        email = userData[2],
                        username = userData[3],
                        password = userData[4],
                        minGoal = userData[5],
                        maxGoal = userData[6]
                    )
                    users.add(user)
                    userCount=+1
                }
            }
        }
    }

    fun loadUser(username: String, password: String): Boolean {
        for (user in users) {
            if (user.username == username && user.password == password) {
                return true
            }
        }
        return false
    }

    fun signInUser(username: String, password: String): Boolean {
        for (user in users) {
            if (user.username == username && user.password == password) {
                currentUser = user
                return true
            }
        }
        return false
    }


    fun getUserID(): Int? {
        return currentUser?.id
    }

    fun addUser(name: String, email: String, username: String, password: String, minGoal: String, maxGoal: String) {
        // Create a new user
        val addUserID = userCount + 1
        val newUser = User(addUserID, name, email, username, password, minGoal, maxGoal)

        // Add the new user to the users list
        users.add(newUser)
        userCount=+1

        // Write the new user's information to the UserList.txt file
        val fileWriter = FileWriter("UserList.txt", true) // true to append to the file
        val bufferedWriter = BufferedWriter(fileWriter)
        bufferedWriter.write("${newUser.id}#${newUser.name}#${newUser.email}#${newUser.username}#${newUser.password}#${newUser.minGoal}#${newUser.maxGoal}\n")
        bufferedWriter.close()
    }

    fun updateUser(name: String, email: String, username: String, password: String, minGoal: String, maxGoal: String) {
        // Find the current user in the list
        val user = users.find { it.id == currentUser?.id }

        // Update the user details
        user?.let {
            it.name = name
            it.email = email
            it.username = username
            it.password = password
            it.minGoal = minGoal
            it.maxGoal = maxGoal
        }

        // Update the user details in the text file
        val file = File(getApplication<Application>().filesDir, "UserList.txt")
        val lines = file.readLines().toMutableList()

        // Find the line with the current user's details
        val index = lines.indexOfFirst { it.startsWith("${currentUser?.id}#") }

        // Replace the line with the updated user details
        if (index != -1) {
            lines[index] = "${currentUser?.id}#$name#$email#$username#$password#$minGoal#$maxGoal"
            file.writeText(lines.joinToString("\n"))
        }
    }

}