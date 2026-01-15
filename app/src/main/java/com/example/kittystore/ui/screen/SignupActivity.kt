package com.example.kittystore.ui.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kittystore.R
import com.example.kittystore.ui.viewmodel.SignupViewModel

class SignupActivity : AppCompatActivity() {

    private val viewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        val goToLogin: TextView = findViewById(R.id.to_login_from_signup_second)
        val btnSignup: Button = findViewById(R.id.btn_signup)

        goToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val inputLogin: EditText = findViewById(R.id.input_login)
        val inputPassword: EditText = findViewById(R.id.input_password)

        btnSignup.setOnClickListener {
            val login = inputLogin.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            val (success, info) = viewModel.btnSignupClicked(login, password)
            Toast.makeText(this, info, Toast.LENGTH_LONG).show()
        }

    }
}