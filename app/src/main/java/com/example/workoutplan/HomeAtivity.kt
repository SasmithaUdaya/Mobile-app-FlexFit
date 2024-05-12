package com.example.workoutplan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.workoutplan.R.id

class HomeAtivity : AppCompatActivity() {

    private lateinit var goView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_ativity)

        goView = findViewById(R.id.logoView)

        goView.setOnClickListener{
            val intent = Intent(this@HomeAtivity, MainActivity::class.java)
            startActivity(intent)
        }

    }


}