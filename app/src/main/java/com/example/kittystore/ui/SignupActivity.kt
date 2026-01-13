package com.example.kittystore.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.kittystore.R

class SignupActivity : AppCompatActivity() {
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

            if (login == "" || password == "") {
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            } else {

            }
        }

    }
}