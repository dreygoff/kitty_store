package com.example.kittystore.ui.screen.main

sealed class UiEvent {
    object NavigateToStore : UiEvent()
    object NavigateToLogin : UiEvent()
    object NavigateToSignup : UiEvent()
}
