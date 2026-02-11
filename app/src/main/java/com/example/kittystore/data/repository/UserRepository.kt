package com.example.kittystore.data.repository

import com.example.kittystore.data.dto.UserDto
import com.example.kittystore.domain.auth.CreateUserResult

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