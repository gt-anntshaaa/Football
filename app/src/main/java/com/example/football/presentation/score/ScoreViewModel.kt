package com.example.football.presentation.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.domain.common.Resource
import com.example.football.domain.common.SingleLiveEvent
import com.example.football.domain.usecase.ClubNameUseCase
import com.example.football.domain.usecase.MatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(private val clubNameUseCase: ClubNameUseCase,
    private val matchUseCase: MatchesUseCase) : ViewModel() {

    private val _listClubName = SingleLiveEvent<Resource<Array<String>>?>()
    val listClubName get() = _listClubName

    private val _matches = SingleLiveEvent<Resource<String>?>()
    val matches get() = _matches

    fun doMatches(nameHost: String, nameGuest: String, scoreHost: String, scoreGuest: String){
        _matches.value = Resource.Loading

        viewModelScope.launch(Dispatchers.Main) {
            _matches.value = matchUseCase(nameHost, nameGuest, scoreHost, scoreGuest)
        }
    }

    fun load(){
        _listClubName.value = Resource.Loading

        viewModelScope.launch(Dispatchers.Main) { _listClubName.value = clubNameUseCase() }
    }

    init {

    }
}