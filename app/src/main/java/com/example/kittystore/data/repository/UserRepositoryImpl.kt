package com.example.kittystore.data.repository

import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun createUser(
        username: String,
        passwordHash: String,
        salt: String
    ): UserRepository.CreateUserResult {


        return UserRepository.CreateUserResult.UsernameTaken
    }
}