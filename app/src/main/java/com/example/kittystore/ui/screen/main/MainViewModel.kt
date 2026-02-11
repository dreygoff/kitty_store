package com.example.kittystore.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    fun onLoginClicked() {
        viewModelScope.launch {
            _events.emit(UiEvent.NavigateToLogin)
        }
    }

    fun onSignupClicked() {
        viewModelScope.launch {
            _events.emit(UiEvent.NavigateToSignup)
        }
    }
}