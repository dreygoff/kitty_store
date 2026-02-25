package com.example.kittystore.ui.screen.login

import androidx.lifecycle.viewModelScope
import com.example.kittystore.R
import com.example.kittystore.domain.usecase.auth.VerifyPasswordResult
import com.example.kittystore.domain.usecase.auth.VerifyPasswordUseCase
import com.example.kittystore.ui.base.BaseStateModelView
import com.example.kittystore.ui.base.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val verifyPasswordUseCase: VerifyPasswordUseCase
) : BaseStateModelView<UiEvent>() {

    fun onLoginClicked(username: String, password: String) {
        viewModelScope.launch {
            val event = launchWithLoading {
                when (verifyPasswordUseCase(username, password)) {
                    VerifyPasswordResult.Success -> UiEvent.NavigateToStore
                    VerifyPasswordResult.InvalidPassword -> UiEvent.ShowToast(
                        UiText.StringResource(R.string.err_invalid_password)
                    )

                    VerifyPasswordResult.UserNotFound -> UiEvent.ShowToast(
                        UiText.StringResource(R.string.err_user_not_found)
                    )
                }
            }
            emitEvent(event)
        }
    }
}