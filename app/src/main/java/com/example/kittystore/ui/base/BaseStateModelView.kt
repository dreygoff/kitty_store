package com.example.kittystore.ui.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseStateModelView<E> : BaseViewModel<E>() {

    protected val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    protected fun setLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    protected suspend fun <T> launchWithLoading(
        block: suspend () -> T
    ): T {
        setLoading(true)
        return try {
            block()
        } finally {
            setLoading(false)
        }
    }
}