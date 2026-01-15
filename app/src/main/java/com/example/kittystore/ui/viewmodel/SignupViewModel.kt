package com.example.kittystore.ui.viewmodel

import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {

    fun btnSignupClicked(login: String, password: String): Pair<Boolean, String> {

        // todo UserRepository.create

        return Pair(true, "Sucksass")
    }
}