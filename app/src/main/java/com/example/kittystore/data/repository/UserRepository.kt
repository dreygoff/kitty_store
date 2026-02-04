package com.example.kittystore.data.repository

import com.example.kittystore.data.dto.UserDto

interface UserRepository {
    suspend fun createUser(
        username: String,
        passwordHash: String,
        salt: String
        ): UserDto
}