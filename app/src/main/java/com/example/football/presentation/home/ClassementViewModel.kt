package com.example.football.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.data.source.local.entity.Classement
import com.example.football.domain.common.Resource
import com.example.football.domain.common.SingleLiveEvent
import com.example.football.domain.usecase.LoadClassementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassementViewModel @Inject constructor
    (private val classementUseCase: LoadClassementUseCase) : ViewModel() {

    private val _classement = SingleLiveEvent<Resource<List<Classement>>?>()
    val classement get() = _classement

    init {

    }

    fun load(){
        _classement.value = Resource.Loading

        viewModelScope.launch(Dispatchers.Main) { _classement.value = classementUseCase() }
    }
}