package com.example.kittystore.data.repository

import com.example.kittystore.data.dto.UserDto
import com.example.kittystore.data.mapper.toDomain
import com.example.kittystore.domain.usecase.auth.CreateUserResult
import com.example.kittystore.domain.repository.UserRepository
import kotlinx.coroutines.delay
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

private const val NETWORK_DELAY = 2000L

class FakeUserRepositoryImpl @Inject constructor() : UserRepository {
    private val users = ConcurrentHashMap<String, UserDto>()

    override suspend fun createUser(
        username: String,
        passwordHash: String,
        salt: String
    ): CreateUserResult {

        //Network delay imitation
        delay(NETWORK_DELAY)

        val userDto = UserDto(
            id = UUID.randomUUID().toString(),
            username = username,
            passwordHash = passwordHash,
            salt = salt
        )

        val existing = users.putIfAbsent(username, userDto)

        return if (existing == null) {
            CreateUserResult.Success(userDto.toDomain())
        } else {
            CreateUserResult.UsernameTaken
        }
    }

    override suspend fun getUserByUsername(username: String): UserDto? {
        return users[username]
    }
}