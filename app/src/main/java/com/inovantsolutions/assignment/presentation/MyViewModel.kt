package com.inovantsolutions.assignment.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inovantsolutions.assignment.data.Repository
import com.inovantsolutions.assignment.domain.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UiSate{
    data object Idea:UiSate
    data object Loading:UiSate
    data class Error(val message:String) :UiSate
    data class Sucsess(val response: Response):UiSate
}

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _uiSate:MutableStateFlow<UiSate> = MutableStateFlow(UiSate.Idea)
    val uiSate = _uiSate.asStateFlow()
    fun getData(){
        _uiSate.value = UiSate.Loading
        try {
            viewModelScope.launch {
                val data= repository.getdate()
                _uiSate.value = UiSate.Sucsess(data)
                Log.d(TAG , data.data.name)
            }
        }catch (e:Exception){
            _uiSate.value = UiSate.Error(e.message.toString())
        }
    }
    init {
        getData()
    }
    companion object{
        const val TAG =  "MainViewModel"
    }
}