package com.example.kittystore.ui.screen.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kittystore.databinding.ActivityLoginBinding
import com.example.kittystore.ui.base.BaseActivity
import com.example.kittystore.ui.base.collectStateAndEvents
import com.example.kittystore.ui.screen.UiState
import com.example.kittystore.ui.screen.UiText
import com.example.kittystore.ui.screen.store.StoreActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater) = ActivityLoginBinding.inflate(inflater)

    override fun setupUi() {
        binding.btnLogin.setOnClickListener {
            viewModel.onLoginClicked(
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
        binding.btnLogin.isEnabled = !state.isLoading
        binding.inputUsername.isEnabled = !state.isLoading
        binding.inputPassword.isEnabled = !state.isLoading
    }

    private fun handleEvent(event: UiEvent) {
        when (event) {
            UiEvent.NavigateToStore -> navigateTo(StoreActivity::class.java)
            is UiEvent.ShowToast -> showToast(event.message)
        }
    }
}