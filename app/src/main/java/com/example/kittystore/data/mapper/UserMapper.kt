package com.example.kittystore.data.mapper

import com.example.kittystore.data.dto.UserDto
import com.example.kittystore.domain.model.User

fun UserDto.toDomain() = User(
    id = id,
    username = username,
    passwordHash = passwordHash,
    salt = salt
)
