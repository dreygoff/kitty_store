package com.example.kittystore.data.mapper

import com.example.kittystore.data.dto.UserDto
import com.example.kittystore.data.local.entity.UserEntity
import com.example.kittystore.domain.model.User

fun UserEntity.toDto(): UserDto {
    return UserDto(
        id = id,
        username = username,
        passwordHash = passwordHash,
        salt = salt
    )
}

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        username = username,
        passwordHash = passwordHash,
        salt = salt
    )
}

fun UserDto.toDomain(): User {
    return User(
        id = id,
        username = username,
        passwordHash = passwordHash,
        salt = salt
    )
}

fun UserDto.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        username = username,
        passwordHash = passwordHash,
        salt = salt
    )
}
