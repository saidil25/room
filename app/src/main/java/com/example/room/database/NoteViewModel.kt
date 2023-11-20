package com.example.room.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.room.database.Note
import com.example.room.database.NoteRepository
import com.example.room.database.NoteRoomDatabase

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val noteDao = NoteRoomDatabase
            .getDatabase(application)?.noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes
    }

    // Metode lainnya untuk interaksi dengan repository jika diperlukan
}
