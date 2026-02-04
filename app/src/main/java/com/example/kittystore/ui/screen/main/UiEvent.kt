package com.example.kittystore.ui.screen.main

sealed class UiEvent {
    object NavigateToLogin: UiEvent()
    object NavigateToSignup: UiEvent()
}
