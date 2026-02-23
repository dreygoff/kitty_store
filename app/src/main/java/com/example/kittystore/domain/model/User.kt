package com.example.kittystore.domain.model

data class User(
    val id: String,
    val username: String,
    val passwordHash: String,
    val salt: String
)