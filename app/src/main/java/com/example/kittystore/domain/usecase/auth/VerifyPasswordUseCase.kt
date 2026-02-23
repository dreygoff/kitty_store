package com.example.kittystore.domain.usecase.auth

import com.example.kittystore.domain.repository.UserRepository
import com.example.kittystore.domain.security.PasswordHasher
import javax.inject.Inject

class VerifyPasswordUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher
) {

    suspend operator fun invoke(
        username: String,
        password: String
    ): VerifyPasswordResult {

        return userRepository.getUserByUsername(username)?.let {
            if (passwordHasher.verifyPassword(password, it.salt, it.passwordHash)) {
                VerifyPasswordResult.Success
            } else {
                VerifyPasswordResult.InvalidPassword
            }
        } ?: VerifyPasswordResult.UserNotFound
    }
}