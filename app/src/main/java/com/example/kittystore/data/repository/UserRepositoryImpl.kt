package com.example.kittystore.data.repository

import com.example.kittystore.data.dto.UserDto
import kotlinx.coroutines.delay
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun createUser(
        username: String,
        passwordHash: String,
        salt: String
    ): UserDto {

        //Network delay imitation
        delay(2000)

        return UserDto(
            id = UUID.randomUUID().toString(),
            username,
            passwordHash,
            salt
        )
    }
}