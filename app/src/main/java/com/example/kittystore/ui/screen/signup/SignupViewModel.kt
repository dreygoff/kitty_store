package com.example.kittystore.ui.screen.signup


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kittystore.domain.usecase.CreateUserUseCase
import com.example.kittystore.ui.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel: ViewModel() {

    private val createUserUseCase = CreateUserUseCase()
    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun btnSignupClicked(username: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val event = try {
                withContext(Dispatchers.IO) { createUserUseCase(username, password) }
                UiEvent.SignupSuccess
            } catch (e: IllegalArgumentException) {
                UiEvent.Error(e.message ?: "Unknown Error")
            }
            _isLoading.value = false
            _events.emit(event)
        }

    }
}