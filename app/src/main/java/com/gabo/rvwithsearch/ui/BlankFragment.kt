package com.gabo.rvwithsearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gabo.rvwithsearch.base.BaseFragmentWithVM
import com.gabo.rvwithsearch.databinding.FragmentBlankBinding

class BlankFragment : BaseFragmentWithVM<MainVM, FragmentBlankBinding>(
    FragmentBlankBinding::inflate,MainVM::class.java,false) {

    override fun setupView(savedInstanceState: Bundle?) {

    }

}