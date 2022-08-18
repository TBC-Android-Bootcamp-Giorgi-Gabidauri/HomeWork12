package com.gabo.rvwithsearch.ui.itemFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gabo.rvwithsearch.R
import com.gabo.rvwithsearch.base.BaseFragment
import com.gabo.rvwithsearch.base.BaseFragmentWithVM
import com.gabo.rvwithsearch.databinding.FragmentItemBinding
import com.gabo.rvwithsearch.model.CharacterModel

class ItemFragment :
    BaseFragmentWithVM<ViewModel, FragmentItemBinding>(FragmentItemBinding::inflate, null, false) {


    private val args: ItemFragmentArgs by navArgs()

    override fun setupView(savedInstanceState: Bundle?) {
        showData(args.character)
        binding.ivImage.setOnClickListener {
            findNavController().navigate(R.id.blankFragment)
        }
    }

    private fun showData(character: CharacterModel) {
        with(binding) {
            tvName.text = getString(R.string.name) + character.name
            tvBirthDay.text = getString(R.string.birthday) + character.birthday
            tvNickName.text = getString(R.string.nickname) + character.nickname
            tvStatus.text = getString(R.string.status) + character.status
            tvPortrayed.text = getString(R.string.portrayed) + character.portrayed
            tvAppearance.text =
                getString(R.string.appearance) + character.appearance.toString().drop(1).dropLast(1)
            tvOccupation.text =
                getString(R.string.occupation) + character.occupation.toString().drop(1).dropLast(1)
            Glide.with(ivImage).load(character.img).into(ivImage)
        }
    }
}

