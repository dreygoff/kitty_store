package com.example.kittystore.data.repository

import com.example.kittystore.data.dto.UserDto
import kotlinx.coroutines.delay
import java.util.UUID

class FakeUserRepositoryImpl : UserRepository {

    val users = mutableMapOf<String, UserDto>()

    override suspend fun createUser(
        username: String,
        passwordHash: String,
        salt: String
    ): UserRepository.CreateUserResult {

        //Network delay imitation
        delay(2000)

        if (!users.containsKey(username)) {
            val user = UserDto(
                id = UUID.randomUUID().toString(),
                username,
                passwordHash,
                salt
            )
            users.put(username, user)
            return UserRepository.CreateUserResult.Success(user)
        } else {
            return UserRepository.CreateUserResult.UsernameTaken
        }
    }
}