package com.igld279.todo_mvvm.ui.newnote

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.igld279.todo_mvvm.R
import com.igld279.todo_mvvm.db.MyNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class NewNoteActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextTODO: EditText
    private lateinit var myNoteEdit: MyNote
    private val newNoteViewModel: NewNoteViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        supportActionBar?.apply {
            title = getString(R.string.new_todo)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextTODO = findViewById(R.id.editTextTODO)

        if (intent.getSerializableExtra("myNoteEdit") != null) {
            myNoteEdit = intent.getSerializableExtra("myNoteEdit") as MyNote
            editTextTitle.setText(myNoteEdit.titleText)
            editTextTODO.setText(myNoteEdit.todoText)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!editTextTitle.text.toString().equals("")) {
            if (intent.getSerializableExtra("myNoteEdit") != null) {
                val myNote = MyNote(myNoteEdit.uid, editTextTitle.text.toString(), editTextTODO.text.toString(), false)
                CoroutineScope(Dispatchers.Main).launch {
                    newNoteViewModel.update(myNote)
                }
            } else {
                val myNote = MyNote(null, editTextTitle.text.toString(), editTextTODO.text.toString(), false)
                CoroutineScope(Dispatchers.Main).launch {
                    newNoteViewModel.insert(myNote)
                }
            }
        }
    }

}