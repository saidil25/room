package com.example.room.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.room.database.Note
import com.example.room.database.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    // LiveData yang akan diobservasi oleh ViewModel atau komponen UI
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        // Lakukan operasi insert pada database
        noteDao.insert(note)
    }

    // Metode lainnya untuk interaksi dengan database jika diperlukan
}
