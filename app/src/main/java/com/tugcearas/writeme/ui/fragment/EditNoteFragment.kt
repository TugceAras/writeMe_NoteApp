package com.tugcearas.writeme.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tugcearas.writeme.R
import com.tugcearas.writeme.data.room.model.NoteEntity
import com.tugcearas.writeme.data.vm.NoteVM
import com.tugcearas.writeme.databinding.FragmentEditNoteBinding
import com.tugcearas.writeme.util.click
import com.tugcearas.writeme.util.toastMessage
import java.util.*

class EditNoteFragment : Fragment(), MenuProvider {

    private val getNotes by navArgs<EditNoteFragmentArgs>()
    private lateinit var binding: FragmentEditNoteBinding
    private val viewModel:NoteVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNoteBinding.inflate(layoutInflater,container,false)
        bindArguments()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this)
        editButtonClick()
    }

    private fun bindArguments(){
        binding.editTitleText.setText(getNotes.getArguments.title)
        binding.editSubtitleText.setText(getNotes.getArguments.subtitle)
        binding.editNoteText.setText(getNotes.getArguments.note)
    }

    private fun editButtonClick(){
       binding.editNoteButton.click {
           updateNotes(view)
           val action = EditNoteFragmentDirections.actionEditNoteFragmentToHomeFragment()
           findNavController().navigate(action)
       }
    }

    private fun updateNotes(view:View?) {
        val title = binding.editTitleText.text.toString()
        val subtitle = binding.editSubtitleText.text.toString()
        val noteDescription = binding.editNoteText.text.toString()
        val d = Date()
        val noteDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val notes = NoteEntity(
            getNotes.getArguments.id,
            title = title,
            subtitle = subtitle,
            note = noteDescription,
            date = noteDate.toString())
        viewModel.updateNotes(notes)
        requireContext().toastMessage("Note successfully edited!")
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == R.id.menuDeleteButton){

            val bottomSheet = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_bottom)

            val yesButton = bottomSheet.findViewById<TextView>(R.id.yesButton)
            val noButton = bottomSheet.findViewById<TextView>(R.id.noButton)
            yesButton?.click {
                viewModel.deleteNotes(getNotes.getArguments.id!!)
                bottomSheet.dismiss()
                val action = EditNoteFragmentDirections.actionEditNoteFragmentToHomeFragment()
                findNavController().navigate(action)
                Toast.makeText(context, "Successfully deleted!", Toast.LENGTH_SHORT).show()
            }
            noButton?.click {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
            return true
        }
        return false
    }

    override fun onDestroyView() {
        activity?.removeMenuProvider(this)
        super.onDestroyView()
    }
}