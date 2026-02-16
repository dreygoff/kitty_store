package com.example.kittystore.domain.auth

import com.example.kittystore.data.repository.UserRepository
import com.example.kittystore.data.security.PasswordHasher
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    // Username must be 5-20 characters long. Available: a-z, A-Z, 0-9, "_"(not first character)
    private val usernameRegex =
        Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{4,19}\$")

    // Password must be 8-25 characters long. Required at least one 0-9, a-z, A-Z, special characters
    private val passwordRegex =
        Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#%^&*()_\\-+=\\[\\]{}|;:',.<>?/~`]).{8,25}$")

    suspend operator fun invoke(username: String, password: String): CreateUserResult {
        if (!usernameRegex.matches(username)) {
            return CreateUserResult.InvalidUsername
        }
        if (!passwordRegex.matches(password)) {
            return CreateUserResult.InvalidPassword
        }

        val salt = PasswordHasher.generateSalt()
        val passwordHash = PasswordHasher.hashPassword(password, salt)

        return userRepository.createUser(username, passwordHash, salt)
    }
}