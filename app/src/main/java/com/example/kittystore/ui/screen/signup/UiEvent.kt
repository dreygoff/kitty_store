package com.example.kittystore.ui.screen.signup

import com.example.kittystore.ui.screen.UiText

sealed class UiEvent {
    data class ShowToast(val message: UiText) : UiEvent()
    object NavigateToLogin : UiEvent()
}