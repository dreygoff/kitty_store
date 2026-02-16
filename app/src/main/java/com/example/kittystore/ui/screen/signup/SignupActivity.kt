package com.example.kittystore.ui.screen.signup

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.kittystore.databinding.ActivitySignupBinding
import com.example.kittystore.ui.base.BaseActivity
import com.example.kittystore.ui.base.collectStateAndEvents
import com.example.kittystore.ui.screen.UiState
import com.example.kittystore.ui.screen.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : BaseActivity<ActivitySignupBinding>() {

    private val viewModel: SignupViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater) = ActivitySignupBinding.inflate(inflater)

    override fun setupUi() {
        binding.toLoginFromSignupSecond.setOnClickListener {
            viewModel.onToLoginFromSignupSecondClicked()
        }
        binding.btnSignup.setOnClickListener {
            viewModel.onSignupClicked(
                binding.inputUsername.text.toString(),
                binding.inputPassword.text.toString()
            )
        }

        collectStateAndEvents(
            viewModel.state,
            viewModel.events,
            renderState = ::renderState,
            handleEvent = ::handleEvent
        )
    }

    private fun renderState(state: UiState) {
        binding.btnSignup.isEnabled = !state.isLoading
        binding.inputUsername.isEnabled = !state.isLoading
        binding.inputPassword.isEnabled = !state.isLoading
    }

    private fun handleEvent(event: UiEvent) {
        when (event) {
            is UiEvent.ShowToast -> showToast(event.message)
            UiEvent.NavigateToLogin -> navigateTo(LoginActivity::class.java)
        }
    }
}