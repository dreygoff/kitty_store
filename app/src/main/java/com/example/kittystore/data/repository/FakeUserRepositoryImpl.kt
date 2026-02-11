package com.example.kittystore.data.repository

import com.example.kittystore.data.dto.UserDto
import com.example.kittystore.domain.auth.CreateUserResult
import kotlinx.coroutines.delay
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

private const val NETWORK_DELAY = 2000L

class FakeUserRepositoryImpl : UserRepository {
    private val users = ConcurrentHashMap<String, UserDto>()

    override suspend fun createUser(
        username: String,
        passwordHash: String,
        salt: String
    ): CreateUserResult {

        //Network delay imitation
        delay(NETWORK_DELAY)

        val user = UserDto(
            id = UUID.randomUUID().toString(),
            username,
            passwordHash,
            salt
        )
        val existing = users.putIfAbsent(username, user)
        return if (existing == null) {
            CreateUserResult.Success(user)
        } else {
            CreateUserResult.UsernameTaken
        }
    }

    override suspend fun getUserByUsername(username: String): UserDto? {
        return users[username]
    }
}