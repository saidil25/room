package com.example.room.database

import androidx.lifecycle.MutableLiveData
import com.example.room.database.Note
import com.example.room.database.NoteDao

class NoteRepository(private val noteDao: NoteDao?) {

    val allNotes: MutableLiveData<List<Note>> = MutableLiveData()

    init {
        // Inisialisasi nilai awal LiveData dengan emptyList()
        allNotes.value = emptyList()
    }

    suspend fun insert(note: Note) {
        noteDao?.insert(note)
    }

    // Metode lainnya untuk interaksi dengan database jika diperlukan
}
