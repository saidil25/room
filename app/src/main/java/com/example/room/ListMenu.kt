package com.example.room

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import com.example.room.database.NoteDao
import com.example.room.database.NoteRoomDatabase
import com.example.room.database.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListMenu : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var noteDao: NoteDao
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_item)

        listView = findViewById(R.id.listView)

        // Inisialisasi NoteDao
        noteDao = NoteRoomDatabase.getDatabase(this)?.noteDao()!!

        // Inisialisasi ViewModel
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        // Mendapatkan semua catatan dari database dan mengamati perubahan
        noteViewModel.allNotes.observe(this, { notes ->
            // Perbarui tampilan atau adapter ListView di sini
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notes)
            listView.adapter = adapter
        })

        // Tangani klik tombol tambah
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add)
        fabAdd.setOnClickListener {
            // Buka layar input data baru
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }
    }

    companion object {
        const val ADD_NOTE_REQUEST = 1
    }
}
