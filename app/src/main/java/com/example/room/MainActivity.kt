package com.example.room

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.example.room.database.Note
import com.example.room.database.NoteRoomDatabase
import androidx.appcompat.widget.AppCompatButton
import android.content.Intent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val txt_title: TextInputEditText by lazy { findViewById(R.id.txt_title) }
    private val txt_desc: TextInputEditText by lazy { findViewById(R.id.txt_desc) }
    private val txt_date: TextInputEditText by lazy { findViewById(R.id.txt_date) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd: AppCompatButton = findViewById(R.id.btnnn)
        btnAdd.setOnClickListener {
            saveNote()

            // Pindah ke ListMenu setelah menyimpan catatan
            val intent = Intent(this, ListMenu::class.java)
            startActivity(intent)
        }
    }

    private fun saveNote() {
        val title = txt_title.text.toString().trim()
        val description = txt_desc.text.toString().trim()
        val date = txt_date.text.toString().trim()

        // Validasi input sebelum menyimpan
        if (title.isEmpty() || description.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Gunakan Coroutines untuk menjalankan operasi database di thread terpisah
        GlobalScope.launch(Dispatchers.IO) {
            // Simpan data ke database
            val note = Note(title = title, description = description, date = date)
            NoteRoomDatabase.getDatabase(this@MainActivity)?.noteDao()?.insert(note)

            // Set result untuk memberitahu ListMenu bahwa data telah ditambahkan
            setResult(Activity.RESULT_OK)

            // Tutup layar input data
            launch(Dispatchers.Main) {
                finish()
            }
        }
    }
}