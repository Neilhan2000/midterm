package com.neil.publisher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val publishButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val navController = findNavController(R.id.myNavHostFragment)

        publishButton.setOnClickListener {
           navController.navigate(R.id.publishFragment)
        }
    }
}