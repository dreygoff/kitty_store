package com.example.kittystore.domain.auth

import com.example.kittystore.data.dto.UserDto

sealed class CreateUserResult {
    data class Success(val user: UserDto) : CreateUserResult()
    object UsernameTaken : CreateUserResult()
}