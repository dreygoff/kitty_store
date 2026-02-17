package com.example.kittystore.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<E> : ViewModel() {
    protected val _events = MutableSharedFlow<E>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()

    protected fun emitEvent(
        event: E
    ) {
        viewModelScope.launch {
            _events.emit(event)
        }
    }
}