package com.example.football.presentation.club

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.domain.common.Resource
import com.example.football.domain.common.SingleLiveEvent
import com.example.football.domain.usecase.DataClubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubViewModel @Inject constructor(private val dataClubUseCase: DataClubUseCase) : ViewModel() {
    private val _club = SingleLiveEvent<Resource<String>?>()
    val club get() = _club

    fun insert(name: String, city: String){
        _club.value = Resource.Loading

        viewModelScope.launch(Dispatchers.Main) {
            _club.value = dataClubUseCase(name, city)
        }
    }
}