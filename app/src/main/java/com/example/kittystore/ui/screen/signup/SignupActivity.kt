package com.example.kittystore.ui.screen.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kittystore.R
import com.example.kittystore.databinding.ActivitySignupBinding
import com.example.kittystore.ui.screen.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private val viewModel: SignupViewModel by viewModels()
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toLoginFromSignupSecond.setOnClickListener {
            viewModel.onToLoginFromSignupSecondClicked()
        }

        binding.btnSignup.setOnClickListener {
            viewModel.onSignupClicked(
                binding.inputLogin.text.toString(), binding.inputPassword.text.toString()
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

    private fun showToast(message: String?) {
        val text = message ?: getString(R.string.err_unknown)
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        val intent = Intent(this@SignupActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun renderState(state: UiState) {
        binding.btnSignup.isEnabled = !state.isLoading
        binding.inputLogin.isEnabled = !state.isLoading
        binding.inputPassword.isEnabled = !state.isLoading
    }

    private fun handleEvent(event: UiEvent) {
        when (event) {
            is UiEvent.ShowToast -> showToast(event.message)
            UiEvent.NavigateToLogin -> navigateToLogin()
            UiEvent.SignupSuccess -> {
                showToast(getString(R.string.msg_signup_success))
                navigateToLogin()
            }
        }
    }
}