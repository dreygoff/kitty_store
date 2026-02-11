package com.example.kittystore.ui.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kittystore.R
import com.example.kittystore.domain.auth.CreateUserResult
import com.example.kittystore.domain.auth.CreateUserUseCase
import com.example.kittystore.ui.screen.UiState
import com.example.kittystore.ui.screen.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun onSignupClicked(username: String, password: String) {
        viewModelScope.launch {

            _state.value = _state.value.copy(isLoading = true)

            try {
                val result = createUserUseCase(username, password)

                when (result) {
                    is CreateUserResult.Success -> {
                        _events.emit(
                            UiEvent.ShowToast(
                                UiText.StringResource(R.string.msg_signup_success)
                            )
                        )
                        _events.emit(UiEvent.NavigateToLogin)
                    }

                    CreateUserResult.UsernameTaken -> {
                        _events.emit(
                            UiEvent.ShowToast(
                                UiText.StringResource(R.string.err_signup_username_taken)
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                _events.emit(
                    UiEvent.ShowToast(
                        e.message?.let { UiText.DynamicString(it) }
                            ?: UiText.StringResource(R.string.err_unknown)
                    )
                )
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    fun onToLoginFromSignupSecondClicked() {
        viewModelScope.launch {
            _events.emit(UiEvent.NavigateToLogin)
        }
    }
}

