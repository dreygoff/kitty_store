package com.example.kittystore.data.repository

import com.example.kittystore.data.dto.UserDto

interface UserRepository {
    suspend fun createUser(
        username: String,
        passwordHash: String,
        salt: String
        ): CreateUserResult

    sealed class CreateUserResult {
        data class Success(val user: UserDto) : CreateUserResult()
        object UsernameTaken : CreateUserResult()
    }
}