package com.example.kittystore.domain.security

interface PasswordHasher {
    fun generateSalt(): String
    fun hashPassword(password: String, salt: String): String
    fun verifyPassword(password: String, salt: String, passwordHash: String): Boolean
}