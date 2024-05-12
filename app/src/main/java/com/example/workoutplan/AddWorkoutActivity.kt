package com.example.workoutplan

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.workoutplan.databinding.ActivityAddWorkoutBinding

class AddWorkoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddWorkoutBinding
    private lateinit var db: NoteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabase(this)

        binding.savebutton.setOnClickListener{
            val title = binding.titleEditText.text.toString()
            val title1 = binding.titleEditText1.text.toString()
            val title2 = binding.titleEditText2.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = Note(0,title,title1,title2,content)
            db.insertNote(note)
            finish()
            Toast.makeText(this,"Workout Saved", Toast.LENGTH_SHORT).show()

        }

    }
}