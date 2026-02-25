package com.example.kittystore.ui.screen.signup

import androidx.lifecycle.viewModelScope
import com.example.kittystore.R
import com.example.kittystore.domain.usecase.auth.CreateUserResult
import com.example.kittystore.domain.usecase.auth.CreateUserUseCase
import com.example.kittystore.ui.base.BaseStateModelView
import com.example.kittystore.ui.base.UiText.StringResource
import com.example.kittystore.ui.screen.signup.UiEvent.ShowToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : BaseStateModelView<UiEvent>() {

    fun onSignupClicked(username: String, password: String) {
        viewModelScope.launch {
            launchWithLoading {
                when (createUserUseCase(username, password)) {
                    is CreateUserResult.Success -> {
                        emitEvent(
                            ShowToast(
                                StringResource(R.string.msg_signup_success)
                            )
                        )
                        emitEvent(UiEvent.NavigateToLogin)
                    }

                    CreateUserResult.UsernameTaken -> emitEvent(
                        ShowToast(
                            StringResource(R.string.err_signup_username_taken)
                        )
                    )

                    CreateUserResult.InvalidPassword -> emitEvent(
                        ShowToast(
                            StringResource(R.string.err_signup_invalid_password)
                        )
                    )

                    CreateUserResult.InvalidUsername -> emitEvent(
                        ShowToast(
                            StringResource(R.string.err_signup_invalid_username)
                        )
                    )
                }
            }
        }
    }

    fun onToLoginFromSignupSecondClicked() = emitEvent(UiEvent.NavigateToLogin)
}

