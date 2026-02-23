package com.example.kittystore.data.repository

import com.example.kittystore.data.dto.UserDto
import com.example.kittystore.domain.auth.CreateUserResult
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    override suspend fun createUser(
        username: String,
        passwordHash: String,
        salt: String
    ): CreateUserResult {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByUsername(username: String): UserDto? {
        TODO("Not yet implemented")
    }
}