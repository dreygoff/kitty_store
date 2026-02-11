package com.example.kittystore.domain.auth

sealed class VerifyPasswordResult {
    object Success : VerifyPasswordResult()
    object UserNotFound : VerifyPasswordResult()
    object InvalidPassword : VerifyPasswordResult()
}