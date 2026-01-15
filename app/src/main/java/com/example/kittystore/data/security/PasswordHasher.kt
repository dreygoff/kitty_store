package com.example.kittystore.data.security

import com.example.kittystore.data.dto.UserDto
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

object PasswordHasher {

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

    fun verifyPassword(user: UserDto, password: String): Boolean {
        val hash = hashPassword(password, user.salt)
        return MessageDigest.isEqual(
            Base64.getDecoder().decode(hash),
            Base64.getDecoder().decode(user.passwordHash)
        )
    }
}