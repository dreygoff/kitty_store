package com.example.kittystore.ui.screen.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kittystore.R
import com.example.kittystore.databinding.ActivityMainBinding
import com.example.kittystore.ui.screen.signup.SignupActivity
import com.example.kittystore.ui.screen.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
           viewModel.onLoginClicked()
        }

        binding.btnSignup.setOnClickListener {
            viewModel.onSignupClicked()
        }

        subscribeViewModel()
    }


    private fun subscribeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.events.collect { event -> handleEvent(event) }
                }
            }
        }
    }

    private fun handleEvent(event: UiEvent) {
        when(event) {
            UiEvent.NavigateToLogin -> navigateToLogin()
            UiEvent.NavigateToSignup -> navigateToSign()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSign() {
        val intent = Intent(this@MainActivity, SignupActivity::class.java)
        startActivity(intent)
    }

}
