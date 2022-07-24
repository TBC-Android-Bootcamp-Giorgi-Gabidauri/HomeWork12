package com.gabo.rvwithsearch.ui.searchFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gabo.rvwithsearch.adapter.CharactersAdapter
import com.gabo.rvwithsearch.base.BaseFragmentWithVM
import com.gabo.rvwithsearch.databinding.FragmentSearchBinding
import com.gabo.rvwithsearch.extensions.onTextChanged
import com.gabo.rvwithsearch.model.CharacterModel
import kotlinx.coroutines.launch

class SearchFragment : BaseFragmentWithVM<SearchViewModel, FragmentSearchBinding>() {
    private lateinit var adapter: CharactersAdapter
    private var list = listOf<CharacterModel>()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate

    override fun getVmClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun setupView(savedInstanceState: Bundle?) {
        setupAdapter()
        adapter.submitList(list)
        setupSearch()
    }

    override fun setupObservers() {
        with(viewModel) {
            viewState.observe(viewLifecycleOwner) {
                if (it.isResponseSuccessful == true) {
                    list = it.characters
                    adapter.submitList(list)
                } else {
                    it.error?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                }
                it.isLoading?.let { isLoading ->
                    binding.loader.isVisible = isLoading
                }
            }
        }
    }

    private fun setupAdapter() {
        adapter = CharactersAdapter { openPhoto(it) }
        with(binding) {
            rv.adapter = adapter
            rv.layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    private fun openPhoto(character: CharacterModel) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToItemFragment(
                character
            )
        )
    }

    private fun setupSearch() {
        lifecycleScope.launch {
            binding.etSearch.onTextChanged { adapter.filter.filter(it) }
        }
    }
}