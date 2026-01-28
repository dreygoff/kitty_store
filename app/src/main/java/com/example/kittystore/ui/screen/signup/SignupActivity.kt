package com.example.kittystore.ui.screen.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kittystore.R
import com.example.kittystore.databinding.ActivitySignupBinding
import com.example.kittystore.ui.UiEvent
import com.example.kittystore.ui.screen.login.LoginActivity
import com.example.kittystore.ui.screen.signup.SignupViewModel
import kotlinx.coroutines.launch
import kotlin.toString

class SignupActivity : AppCompatActivity() {

    private val viewModel: SignupViewModel by viewModels()
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toLoginFromSignupSecond.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignup.setOnClickListener {
            viewModel.btnSignupClicked(
                binding.inputLogin.text.toString(),
                binding.inputPassword.text.toString()
            )
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){

                launch{
                    viewModel.isLoading.collect { loading ->
                        binding.btnSignup.isEnabled = !loading
                        binding.inputLogin.isEnabled = !loading
                        binding.inputPassword.isEnabled = !loading
                    }
                }

                launch{
                    viewModel.events.collect { event ->
                        when (event) {
                            is UiEvent.Error -> {
                                Toast.makeText(
                                    this@SignupActivity,
                                    "Error: ${event.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            is UiEvent.ShowToast -> {
                                Toast.makeText(
                                    this@SignupActivity, event.message, Toast.LENGTH_SHORT
                                ).show()
                            }

                            UiEvent.SignupSuccess -> {
                                Toast.makeText(
                                    this@SignupActivity,
                                    "Registration successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(
                                    Intent(
                                        this@SignupActivity,
                                        LoginActivity::class.java
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}