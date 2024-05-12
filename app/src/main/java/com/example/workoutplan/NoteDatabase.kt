package com.example.workoutplan

import android.adservices.adid.AdId
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDatabase(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "notesapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allnotes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_TITLE1 = "title1"
        private const val COLUMN_TITLE2 = "title2"
        private const val COLUMN_CONTENT = "content"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_TITLE1 TEXT, $COLUMN_TITLE2 TEXT, $COLUMN_CONTENT TEXT)"
        db?.execSQL(createTableQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery) //remove the table that table name is exist
        onCreate(db) //create new table
    }

    fun insertNote(note: Note){
        val db = writableDatabase
        val values = ContentValues().apply { //store the values associates with column names
            put(COLUMN_TITLE, note.title)
            put(COLUMN_TITLE1, note.title1)
            put(COLUMN_TITLE2, note.title2)
            put(COLUMN_CONTENT, note.content)
        }

        db.insert(TABLE_NAME, null, values) //insert that data to the database
        db.close()
    }

    fun getAllNotes(): List<Note>{
    //get the data from the database
        val notesList = mutableListOf<Note>()

        val db = readableDatabase

        val query = "SELECT * FROM $TABLE_NAME"

        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val title1 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE1))
            val title2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE2))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            val note = Note(id, title, title1, title2, content)
            notesList.add(note)
        }

        cursor.close()
        db.close()
        return notesList

    }

    fun updateNote(note: Note){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_TITLE1, note.title1)
            put(COLUMN_TITLE2, note.title2)
            put(COLUMN_CONTENT, note.content)
        }

        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(note.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getNoteById(noteId: Int): Note{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $noteId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val title1 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE1))
        val title2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE2))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

        cursor.close()
        db.close()
        return Note(id, title, title1, title2, content)
    }

    fun deleteNote(noteId: Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(noteId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }
}