package com.example.kittystore.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <E> LifecycleOwner.collectEvents(
    events: Flow<E>,
    handleEvent: (E) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            events.collect(handleEvent)
        }
    }
}

fun <S, E> LifecycleOwner.collectStateAndEvents(
    state: Flow<S>,
    events: Flow<E>,
    renderState: (S) -> Unit,
    handleEvent: (E) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch { state.collect(renderState) }
            launch { events.collect(handleEvent) }
        }
    }
}