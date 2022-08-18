package com.gabo.rvwithsearch.ui

import android.util.Log.d
import androidx.lifecycle.ViewModel

class MainVM: ViewModel() {

    override fun onCleared() {
        d("viewmodeloncleared","Main viewModel  Cleared")
        super.onCleared()
    }
}