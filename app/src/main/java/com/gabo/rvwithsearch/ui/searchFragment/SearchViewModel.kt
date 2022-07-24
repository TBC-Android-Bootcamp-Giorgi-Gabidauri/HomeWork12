package com.gabo.rvwithsearch.ui.searchFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabo.rvwithsearch.model.CharacterModel
import com.gabo.rvwithsearch.service.RetrofitInstance.retrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val viewState: MutableLiveData<ViewState> = MutableLiveData()

    private fun currentViewState(): ViewState = viewState.value!!

    init {
        viewState.value = ViewState()
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            viewState.postValue(currentViewState().copy(isLoading = true))
            val response = retrofitService?.getCharacters()
            if (response!!.isSuccessful) {
                viewState.postValue(
                    currentViewState().copy(
                        isLoading = false,
                        isResponseSuccessful = true,
                        characters = response.body()!!
                    )
                )
            } else {
                viewState.postValue(
                    currentViewState().copy(
                        isLoading = false,
                        isResponseSuccessful = false,
                        error = "Something went Wrong"
                    )
                )
            }
        }
    }

    data class ViewState(
        val isResponseSuccessful: Boolean? = null,
        val characters: List<CharacterModel> = listOf(),
        val error: String? = null,
        val isLoading: Boolean? = null
    )
}