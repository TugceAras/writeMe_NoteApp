package com.tugcearas.writeme.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tugcearas.writeme.R
import com.tugcearas.writeme.data.room.model.NoteEntity
import com.tugcearas.writeme.data.vm.NoteVM
import com.tugcearas.writeme.databinding.FragmentHomeBinding
import com.tugcearas.writeme.ui.adapter.NoteAdapter
import com.tugcearas.writeme.util.click

class HomeFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NoteVM by viewModels()
    private var oldNotes = listOf<NoteEntity>()
    private val adapter by lazy { NoteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this)
        initObserver()
        addNotesButton()
    }

    private fun initObserver() {
        viewModel.getAllNotes().observe(viewLifecycleOwner) {
            oldNotes = it
            adapter.searchFiltering(it)
            binding.homeRecyclerView.adapter = adapter
        }
    }

    private fun addNotesButton() {
        binding.addNoteButton.click {
            val action = HomeFragmentDirections.actionHomeFragmentToCreateNoteFragment()
            findNavController().navigate(action)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_menu, menu)
        val getIcon = menu.findItem(R.id.app_bar_search)
        val searchAction = getIcon.actionView as SearchView
        searchAction.queryHint = "Enter notes..."
        searchAction.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchNotes(it)
                } ?: kotlin.run {
                    adapter.searchFiltering(oldNotes)
                }
                return true
            }
        })
    }

    fun searchNotes(newText: String) {
        adapter.searchFiltering(
            oldNotes.filter {
                it.title.contains(newText) || it.subtitle.contains(newText)
            }
        )
        binding.homeRecyclerView.adapter = adapter
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        activity?.removeMenuProvider(this)
        super.onDestroyView()
    }
}