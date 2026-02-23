package com.example.kittystore.domain.usecase.auth

import com.example.kittystore.domain.model.User

sealed class CreateUserResult {
    data class Success(val user: User) : CreateUserResult()
    object UsernameTaken : CreateUserResult()
    object InvalidUsername : CreateUserResult()
    object InvalidPassword : CreateUserResult()
}