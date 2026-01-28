package com.example.kittystore.ui

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
    data class Error(val message: String) : UiEvent()
    data object SignupSuccess: UiEvent()
}