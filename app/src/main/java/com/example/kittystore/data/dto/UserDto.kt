package com.example.kittystore.data.dto

data class UserDto(
    val id: String,
    val username: String,
    val passwordHash: String,
    val salt: String
)