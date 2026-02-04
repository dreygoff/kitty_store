package com.example.kittystore.ui.screen.signup

sealed class UiEvent {
    data class ShowToast(val message: String?) : UiEvent()
    data object SignupSuccess: UiEvent()
    object NavigateToLogin : UiEvent()
}