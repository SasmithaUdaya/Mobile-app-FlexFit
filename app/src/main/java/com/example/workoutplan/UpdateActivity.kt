package com.example.workoutplan

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.workoutplan.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NoteDatabase
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabase(this)

        noteId = intent.getIntExtra("note_id", -1)
        if(noteId == -1){
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.updatetitleEditText.setText(note.title)
        binding.updatetitleEditText1.setText(note.title1)
        binding.updatetitleEditText2.setText(note.title2)
        binding.updatecontentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updatetitleEditText.text.toString()
            val newTitle1 = binding.updatetitleEditText1.text.toString()
            val newTitle2 = binding.updatetitleEditText2.text.toString()
            val newContent = binding.updatecontentEditText.text.toString()
            val updateNote = Note(noteId, newTitle, newTitle1, newTitle2, newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }



    }
}