package com.example.kittystore.data

import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64
import java.util.UUID

class User private constructor(
    val id: String,
    val username: String,
    val passwordHash: String,
    val salt: String
) {
    companion object {
        fun create(username: String, password: String): Result<User> = runCatching {

            // Username must be 5-20 characters long. Available: a-z, A-Z, 0-9, "_"(not first character)
            // Password must be 8-25 characters long. Required at least one 0-9, a-z, A-Z, special digit
            val usernameRegex = Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{4,19}\$")
            val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#%^&*()_\\-+=\\[\\]{}|;:',.<>?/~`]).{8,25}$")
            require(usernameRegex.matches(username))
            require(passwordRegex.matches(password))

            val salt = generateSalt()
            val passwordHash = hashPassword(password, salt)

            User(
                id = UUID.randomUUID().toString(),
                username,
                passwordHash,
                salt
            )
        }

        fun generateSalt(): String {
            val salt = ByteArray(16)
            val random = SecureRandom()
            random.nextBytes(salt)
            return Base64.getEncoder().encodeToString(salt)
        }

        // PBKDF2 should be used instead of SHA-256 (SHA-256 is used here for educational purposes).
        fun hashPassword(password: String, salt: String): String {
            val md = MessageDigest.getInstance("SHA-256")
            val saltBytes = Base64.getDecoder().decode(salt)
            md.update(saltBytes)
            val passwordHash = md.digest(password.toByteArray())
            return Base64.getEncoder().encodeToString(passwordHash)
        }
    }

    fun verifyPassword(password: String): Boolean {
        val hash = hashPassword(password, salt)
        return MessageDigest.isEqual(
            Base64.getDecoder().decode(hash),
            Base64.getDecoder().decode(passwordHash)
        )
    }
}
