package com.igld279.todo_mvvm.ui.notes

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.igld279.todo_mvvm.R
import com.igld279.todo_mvvm.db.MyNote
import com.igld279.todo_mvvm.ui.newnote.NewNoteActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class NotesFragment : Fragment(), OnCustomClickListener {

    private lateinit var root: View
    private lateinit var myNotes: List<MyNote>
    private lateinit var mainRecyclerView: RecyclerView
    private var adapter : CustomAdapter? = null
    private val notesViewModel: NotesViewModel by inject()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_notes, container, false)
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainRecyclerView = root.findViewById(R.id.recyclerview)
        mainRecyclerView.layoutManager = LinearLayoutManager(root.context)
        notesViewModel.getNotesLiveData().observe(viewLifecycleOwner, Observer { myNotes ->
            adapter = CustomAdapter(myNotes, this)
            mainRecyclerView.adapter = adapter
        })
    }


    private fun setCustomAdapter(myNotes: List<MyNote>){
        if (adapter == null) {
            adapter = CustomAdapter(myNotes, this)
            mainRecyclerView.adapter = adapter
        } else {
            adapter!!.refreshNotes(myNotes)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_all -> {
                CoroutineScope(Dispatchers.IO).launch {
                    myNotes = notesViewModel.getAllNotes()
                    withContext(Dispatchers.Main) {
                        setCustomAdapter(myNotes)
                    }
                }
                return true
            }
            R.id.action_active -> {
                CoroutineScope(Dispatchers.IO).launch {
                    myNotes = notesViewModel.getAllNotesActive()
                    withContext(Dispatchers.Main) {
                        setCustomAdapter(myNotes)
                    }
                }
                return true
            }
            R.id.action_completed -> {
                CoroutineScope(Dispatchers.IO).launch {
                    myNotes = notesViewModel.getAllNotesCompleted()
                    withContext(Dispatchers.Main) {
                        setCustomAdapter(myNotes)
                    }
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onItemCheckBoxClick(view: View, item: MyNote) {
        view as CheckBox
        if (view.isChecked) {
            item.done = true
            CoroutineScope(Dispatchers.Main).launch {
                notesViewModel.updateNote(item)
            }
        } else if (!view.isChecked){
            item.done = false
            CoroutineScope(Dispatchers.Main).launch {
                notesViewModel.updateNote(item)
            }
        }
    }

    override fun onItemClick(item: MyNote, position: Int) {
        val intent = Intent(context, NewNoteActivity::class.java)
        intent.putExtra("myNoteEdit", item)
        context?.startActivity(intent)
        Toast.makeText(context, "clicked $position", Toast.LENGTH_SHORT).show()
    }

    override fun onMenuItemClickListenerEdit(item: MyNote) {
        val intent = Intent(context, NewNoteActivity::class.java)
        intent.putExtra("myNoteEdit", item)
        context?.startActivity(intent)
        Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
    }

    override fun onMenuItemClickListenerDelete(item: MyNote) {
        CoroutineScope(Dispatchers.Main).launch {
            notesViewModel.deleteNote(item)
            Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
        }
    }

}