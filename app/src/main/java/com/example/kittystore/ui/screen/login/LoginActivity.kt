package com.example.kittystore.ui.screen.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kittystore.databinding.ActivityLoginBinding
import com.example.kittystore.ui.screen.UiState
import com.example.kittystore.ui.screen.UiText
import com.example.kittystore.ui.screen.store.StoreActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            viewModel.onLoginClicked(
                binding.inputUsername.text.toString(),
                binding.inputPassword.text.toString()
            )
        }

        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.state.collect { state -> renderState(state) }
                }

                launch {
                    viewModel.events.collect { event -> handleEvent(event) }
                }
            }
        }
    }

    private fun showToast(message: UiText) {
        Toast.makeText(this, message.asString(this), Toast.LENGTH_SHORT).show()
    }

    private fun navigateToStore() {
        val intent = Intent(this@LoginActivity, StoreActivity::class.java)
        startActivity(intent)
    }

    private fun renderState(state: UiState) {
        binding.btnLogin.isEnabled = !state.isLoading
        binding.inputUsername.isEnabled = !state.isLoading
        binding.inputPassword.isEnabled = !state.isLoading
    }

    private fun handleEvent(event: UiEvent) {
        when (event) {
            UiEvent.NavigateToStore -> navigateToStore()
            is UiEvent.ShowToast -> showToast(event.message)
        }
    }
}