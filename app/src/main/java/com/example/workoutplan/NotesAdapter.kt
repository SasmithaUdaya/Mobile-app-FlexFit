package com.example.workoutplan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes: List<Note>, context: Context): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val db: NoteDatabase = NoteDatabase(context)

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val titleTextViewDate: TextView = itemView.findViewById(R.id.titleTextView)
        val titleTextViewWorkoutType: TextView = itemView.findViewById(R.id.workoutType)
        val titleTextViewWorkoutDuration: TextView = itemView.findViewById(R.id.workoutduration)
        val titleTextViewContent: TextView = itemView.findViewById(R.id.workoutContent)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton )
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton )



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent, false)
        return  NoteViewHolder(view)

    }

    override fun getItemCount(): Int = notes.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextViewDate.text = note.title2
        holder.titleTextViewWorkoutType.text = note.title
        holder.titleTextViewWorkoutDuration.text = note.title1
        holder.titleTextViewContent.text = note.content

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener{
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Workout Plan Deleted", Toast.LENGTH_SHORT).show()

        }
    }

    fun refreshData(newNotes: List<Note>){
        notes = newNotes
        notifyDataSetChanged()

    }



}
