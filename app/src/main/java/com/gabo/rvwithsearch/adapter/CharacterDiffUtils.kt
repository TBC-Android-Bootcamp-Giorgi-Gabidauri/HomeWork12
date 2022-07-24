package com.gabo.rvwithsearch.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gabo.rvwithsearch.model.CharacterModel

class CharacterDiffUtils : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel) =
        oldItem.char_id == newItem.char_id


    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel) =
        oldItem == newItem
}