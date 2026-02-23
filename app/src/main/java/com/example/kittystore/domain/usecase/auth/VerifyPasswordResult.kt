package com.example.kittystore.domain.usecase.auth

sealed class VerifyPasswordResult {
    object Success : VerifyPasswordResult()
    object UserNotFound : VerifyPasswordResult()
    object InvalidPassword : VerifyPasswordResult()
}