package com.example.room

import NoteViewModel
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.room.database.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListMenu : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_item)

        listView = findViewById(R.id.listView)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this, { notes ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notes)
            listView.adapter = adapter
        })

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
