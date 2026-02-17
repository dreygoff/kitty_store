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

        return userRepository.getUserByUsername(username)?.let {
            if (PasswordHasher.verifyPassword(it, password)) {
                VerifyPasswordResult.Success
            } else {
                VerifyPasswordResult.InvalidPassword
            }
        } ?: VerifyPasswordResult.UserNotFound
    }
}