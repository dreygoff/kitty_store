package com.example.kittystore.ui.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kittystore.data.repository.UserRepository
import com.example.kittystore.domain.usecase.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _events = MutableSharedFlow<UiEvent>(
        replay = 0,
        extraBufferCapacity = 1
    )
    val events = _events.asSharedFlow()

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun onSignupClicked(username: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val event = try {
                withContext(Dispatchers.IO) {
                    val result = createUserUseCase(username, password)
                    when (result) {
                        is UserRepository.CreateUserResult.Success -> UiEvent.SignupSuccess
                        UserRepository.CreateUserResult.UsernameTaken -> UiEvent.UsernameTaken
                    }
                }
            } catch (e: IllegalArgumentException) {
                UiEvent.ShowToast(e.message)
            }

            _state.value = _state.value.copy(isLoading = false)
            _events.emit(event)
        }
    }

    fun onToLoginFromSignupSecondClicked() {
        viewModelScope.launch {
            _events.emit(UiEvent.NavigateToLogin)
        }
    }
}

