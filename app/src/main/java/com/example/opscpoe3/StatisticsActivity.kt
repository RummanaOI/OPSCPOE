package com.example.opscpoe3

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.opscpoe3.databinding.ActivityStatisticsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class StatisticsActivity : AppCompatActivity() {

    private var _binding: ActivityStatisticsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //bar graph

        _binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            statsBarChart.animation.duration = animationDuration
            statsBarChart.animate(barSet)

            statsLineChart.gradientFillColors =
                intArrayOf(
                    Color.parseColor("#81FFFFFF"),
                    Color.TRANSPARENT
                )
            statsLineChart.animation.duration = animationDuration
            statsLineChart.onDataPointTouchListener = { index, _, _ ->
                txtStatsLineGraphData.text =
                    lineSet.toList()[index]
                        .second
                        .toString()
            }
            statsLineChart.animate(lineSet)
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
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //dummy data for graph
    companion object {

        private val barSet = listOf(
            "JAN" to 4F,
            "FEB" to 7F,
            "MAR" to 2F,
            "MAY" to 2.3F,
            "APR" to 5F,
            "JUN" to 4F
        )

        private val lineSet = listOf(
            "label1" to 5f,
            "label2" to 4.5f,
            "label3" to 4.7f,
            "label4" to 3.5f,
            "label5" to 3.6f,
            "label6" to 7.5f,
            "label7" to 7.5f,
            "label8" to 10f,
            "label9" to 5f,
            "label10" to 6.5f,
            "label11" to 3f,
            "label12" to 4f
        )

        private const val animationDuration = 1000L
    }
}