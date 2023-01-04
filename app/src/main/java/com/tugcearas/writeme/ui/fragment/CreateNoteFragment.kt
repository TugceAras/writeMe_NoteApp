package com.tugcearas.writeme.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tugcearas.writeme.data.room.model.NoteEntity
import com.tugcearas.writeme.data.vm.NoteVM
import com.tugcearas.writeme.databinding.FragmentCreateNoteBinding
import com.tugcearas.writeme.util.click
import com.tugcearas.writeme.util.toastMessage
import java.util.*

class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    private val viewModel:NoteVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createButtonClick()
    }

    private fun createButtonClick(){
        binding.saveNoteButton.click {
            createNote(view)
            val action = CreateNoteFragmentDirections.actionCreateNoteFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }

    private fun createNote(view: View?) {

        val title = binding.createTitleText.text.toString()
        val subtitle = binding.createSubtitleText.text.toString()
        val noteDescription = binding.createNoteText.text.toString()
        val d = Date()
        val noteDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val notes = NoteEntity(null, title = title, subtitle = subtitle, note = noteDescription, date = noteDate.toString())
        viewModel.addNotes(notes)
        requireContext().toastMessage("Note created!")
    }
}