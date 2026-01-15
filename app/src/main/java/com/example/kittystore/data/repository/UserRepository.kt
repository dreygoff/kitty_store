package com.example.kittystore.data.repository

import com.example.kittystore.data.dto.UserDto
import com.example.kittystore.data.security.PasswordHasher
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64
import java.util.UUID
import kotlin.text.matches

class UserRepository {
    fun create(username: String, password: String): Result<UserDto> = runCatching {
        validateUsername(username)
        validatePassword(password)

        val salt = PasswordHasher.generateSalt()
        val passwordHash = PasswordHasher.hashPassword(password, salt)

        UserDto(
            id = UUID.randomUUID().toString(),
            username,
            passwordHash,
            salt
        )
    }

    fun verifyPassword(user: UserDto, password: String): Boolean {
        return PasswordHasher.verifyPassword(user, password)
    }

    private fun validateUsername(username: String) {
        // Username must be 5-20 characters long. Available: a-z, A-Z, 0-9, "_"(not first character)
        val usernameRegex = Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{4,19}\$")
        require(usernameRegex.matches(username))
    }

    private fun validatePassword(password: String) {
        // Password must be 8-25 characters long. Required at least one 0-9, a-z, A-Z, special characters
        val passwordRegex =
            Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#%^&*()_\\-+=\\[\\]{}|;:',.<>?/~`]).{8,25}$")
        require(passwordRegex.matches(password))
    }

}