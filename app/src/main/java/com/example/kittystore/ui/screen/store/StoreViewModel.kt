package com.example.kittystore.ui.screen.store

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StoreViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<StoreItem>>(emptyList())
    val items = _items.asStateFlow()
}