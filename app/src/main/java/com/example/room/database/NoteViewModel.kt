import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.room.database.Note
import com.example.room.database.NoteRepository
import com.example.room.database.NoteRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val noteDao = NoteRoomDatabase.getDatabase(application)?.noteDao()
        repository = NoteRepository(noteDao!!)
        allNotes = repository.allNotes
    }

    fun insert(note: Note) {
        GlobalScope.launch(Dispatchers.IO) {
            repository.insert(note)
        }
    }

    // Metode lainnya untuk interaksi dengan repository jika diperlukan
}
