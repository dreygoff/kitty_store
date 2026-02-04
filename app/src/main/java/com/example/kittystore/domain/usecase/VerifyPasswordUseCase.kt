package com.example.kittystore.domain.usecase

import com.example.kittystore.data.dto.UserDto
import com.example.kittystore.data.security.PasswordHasher

class VerifyPasswordUseCase {
    operator fun invoke(
        user: UserDto,
        password: String
    ): Boolean {
        return PasswordHasher.verifyPassword(user, password)
    }
}