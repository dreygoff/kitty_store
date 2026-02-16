package com.example.kittystore.ui.screen.main


import com.example.kittystore.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<UiEvent>() {

    fun onLoginClicked() = emitEvent(UiEvent.NavigateToLogin)
    fun onSignupClicked() = emitEvent(UiEvent.NavigateToSignup)
}