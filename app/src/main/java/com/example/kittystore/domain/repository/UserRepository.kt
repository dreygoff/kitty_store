package com.example.kittystore.domain.repository

import com.example.kittystore.data.dto.UserDto
import com.example.kittystore.domain.usecase.auth.CreateUserResult

interface UserRepository {
    suspend fun createUser(
        username: String,
        passwordHash: String,
        salt: String
    ): CreateUserResult

    suspend fun getUserByUsername(
        username: String
    ): UserDto?
}