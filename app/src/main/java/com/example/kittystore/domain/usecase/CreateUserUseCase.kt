package com.example.kittystore.domain.usecase

import com.example.kittystore.data.dto.UserDto
import com.example.kittystore.data.security.PasswordHasher
import java.util.UUID

class CreateUserUseCase {

    // Username must be 5-20 characters long. Available: a-z, A-Z, 0-9, "_"(not first character)
    val usernameRegex =
        Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{4,19}\$")

    // Password must be 8-25 characters long. Required at least one 0-9, a-z, A-Z, special characters
    val passwordRegex =
        Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#%^&*()_\\-+=\\[\\]{}|;:',.<>?/~`]).{8,25}$")

    suspend operator fun invoke(username: String, password: String): UserDto {
        validateUsername(username)
        validatePassword(password)

        val salt = PasswordHasher.generateSalt()
        val passwordHash = PasswordHasher.hashPassword(password, salt)

        //Network delay imitation
        Thread.sleep(2000)

        return UserDto(
            id = UUID.randomUUID().toString(),
            username,
            passwordHash,
            salt
        )
    }

    private fun validateUsername(username: String) {
        require(usernameRegex.matches(username)) {
            "Username must be 5-20 characters, a-z, A-Z, 0-9, '_' (not first character)"
        }
    }

    private fun validatePassword(password: String) {
        require(passwordRegex.matches(password)) {
            "Password must be 8-25 chars, contain upper, lower, digit and special char"
        }
    }
}