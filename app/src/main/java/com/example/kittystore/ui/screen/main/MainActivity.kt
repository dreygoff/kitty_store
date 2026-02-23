package com.example.kittystore.ui.screen.main

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.kittystore.databinding.ActivityMainBinding
import com.example.kittystore.ui.base.BaseActivity
import com.example.kittystore.ui.base.collectEvents
import com.example.kittystore.ui.screen.login.LoginActivity
import com.example.kittystore.ui.screen.signup.SignupActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater) = ActivityMainBinding.inflate(inflater)

    override fun setupUi() {
        binding.btnLogin.setOnClickListener {
            viewModel.onLoginClicked()
        }

        binding.btnSignup.setOnClickListener {
            viewModel.onSignupClicked()
        }

        collectEvents(
            events = viewModel.events,
            handleEvent = ::handleEvent
        )
    }

    private fun handleEvent(event: UiEvent) {
        when (event) {
            UiEvent.NavigateToLogin -> navigateTo(LoginActivity::class.java)
            UiEvent.NavigateToSignup -> navigateTo(SignupActivity::class.java)
        }
    }
}
