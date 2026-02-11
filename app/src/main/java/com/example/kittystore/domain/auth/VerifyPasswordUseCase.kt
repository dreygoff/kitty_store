package com.example.kittystore.domain.auth

import com.example.kittystore.data.repository.UserRepository
import com.example.kittystore.data.security.PasswordHasher
import javax.inject.Inject

class VerifyPasswordUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        username: String,
        password: String
    ): VerifyPasswordResult {

        val user = userRepository.getUserByUsername(username)
            ?: return VerifyPasswordResult.UserNotFound

        return if (PasswordHasher.verifyPassword(user, password))
            VerifyPasswordResult.Success
        else
            VerifyPasswordResult.InvalidPassword
    }
}