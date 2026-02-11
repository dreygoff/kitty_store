package com.example.kittystore.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kittystore.R
import com.example.kittystore.domain.auth.VerifyPasswordResult
import com.example.kittystore.domain.auth.VerifyPasswordUseCase
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
class LoginViewModel @Inject constructor(
    private val verifyPasswordUseCase: VerifyPasswordUseCase
) : ViewModel() {

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun onLoginClicked(username: String, password: String) {
        viewModelScope.launch {

            _state.value = _state.value.copy(isLoading = true)

            val event = try {
                val authResult = verifyPasswordUseCase(username, password)

                when (authResult) {
                    VerifyPasswordResult.InvalidPassword -> UiEvent.ShowToast(
                        UiText.StringResource(R.string.err_invalid_password)
                    )

                    VerifyPasswordResult.Success -> UiEvent.NavigateToStore
                    VerifyPasswordResult.UserNotFound -> UiEvent.ShowToast(
                        UiText.StringResource(R.string.err_user_not_found)
                    )
                }
            } catch (e: Exception) {
                UiEvent.ShowToast(
                    e.message?.let { UiText.DynamicString(it) }
                        ?: UiText.StringResource(R.string.err_unknown)
                )
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }

            _events.emit(event)

        }
    }
}