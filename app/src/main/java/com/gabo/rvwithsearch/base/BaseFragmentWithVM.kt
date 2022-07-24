package com.gabo.rvwithsearch.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentWithVM<VM : ViewModel, B : ViewBinding> : Fragment() {
    private lateinit var _viewModel: VM
    val viewModel get() = _viewModel

    private var _binding: B? = null
    val binding get() = _binding!!

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    abstract fun getVmClass(): Class<VM>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vmClass = getVmClass()
        _viewModel = ViewModelProvider(this)[vmClass]
        setupView(savedInstanceState)
        setupObservers()
    }

    abstract fun setupView(savedInstanceState: Bundle?)

    abstract fun setupObservers()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}