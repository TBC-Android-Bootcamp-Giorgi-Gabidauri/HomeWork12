package com.gabo.rvwithsearch.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.util.zip.Inflater

typealias inflate<B> = (LayoutInflater, ViewGroup?, Boolean) -> B

abstract class BaseFragmentWithVM<VM : ViewModel, B : ViewBinding>(
    private val inflate: inflate<B>,
    private val viewModelClass: Class<VM>?, useSharedVM: Boolean
) : Fragment() {
    private val _viewModel: VM? by lazy{
        if (useSharedVM){
            viewModelClass?.let {
                ViewModelProvider(requireActivity())[viewModelClass]
            }
        }else{
            viewModelClass?.let {
                ViewModelProvider(this)[viewModelClass]
            }
        }
    }
    val viewModel get() = _viewModel!!

    private var _binding: B? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(savedInstanceState)
    }

    abstract fun setupView(savedInstanceState: Bundle?)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}