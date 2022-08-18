package com.gabo.rvwithsearch.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabo.rvwithsearch.R
import com.gabo.rvwithsearch.databinding.ItemViewCharacterBinding
import com.gabo.rvwithsearch.model.CharacterModel

class CharactersAdapter(private val itemClick: (CharacterModel) -> Unit) :
    ListAdapter<CharacterModel, CharactersAdapter.CharacterVH>(CharacterDiffUtils()), Filterable {
    private var binding: ItemViewCharacterBinding? = null
    private var originalList: List<CharacterModel> = currentList.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemViewCharacterBinding.inflate(inflater, parent, false)
        return CharacterVH(binding!!)
    }

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        holder.bind(getItem(position), itemClick)
    }

    inner class CharacterVH(private val binding: ItemViewCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CharacterModel, itemClick: (CharacterModel) -> Unit) {
            with(binding) {
                Glide.with(ivImage).load(model.img).placeholder(R.drawable.ic_launcher_background)
                    .into(ivImage)
                ivImage.setOnClickListener { itemClick(model) }
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                return FilterResults().apply {
                    values = if (constraint.isNullOrEmpty())
                        originalList
                    else
                        onFilter(originalList, constraint.toString())
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                submitList(results?.values as? List<CharacterModel>, true)
            }
        }
    }

    override fun submitList(list: List<CharacterModel>?) {
        submitList(list, false)
    }

    private fun onFilter(list: List<CharacterModel>, constraint: String): List<CharacterModel> {
        return list.filter {
            it.name.lowercase().contains(constraint.lowercase()) || it.nickname.lowercase()
                .contains(constraint.lowercase())
        }
            .sortedByDescending { it.name.lowercase() }
    }

    private fun submitList(list: List<CharacterModel>?, filtered: Boolean) {
        if (!filtered) originalList = list ?: listOf()
        super.submitList(list)
    }
}
