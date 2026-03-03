package com.example.kittystore.data.repository

import com.example.kittystore.data.dto.UserDto
import com.example.kittystore.data.local.dao.UserDao
import com.example.kittystore.data.local.entity.UserEntity
import com.example.kittystore.data.mapper.toDomain
import com.example.kittystore.data.mapper.toDto
import com.example.kittystore.domain.repository.UserRepository
import com.example.kittystore.domain.usecase.auth.CreateUserResult
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun createUser(
        username: String,
        passwordHash: String,
        salt: String
    ): CreateUserResult {
        val existingUser = userDao.getUserByUsername(username)
        if (existingUser != null) {
            return CreateUserResult.UsernameTaken
        }

        val userEntity = UserEntity(
            id = UUID.randomUUID().toString(),
            username = username,
            passwordHash = passwordHash,
            salt = salt
        )
        userDao.insertUser(userEntity)
        return CreateUserResult.Success(userEntity.toDomain())
    }

    override suspend fun getUserByUsername(username: String): UserDto? {
        return userDao.getUserByUsername(username)?.toDto()
    }
}
