package com.example.kittystore.ui.screen.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kittystore.domain.usecase.store.GetStoreItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val getStoreItemUseCase: GetStoreItemUseCase
) : ViewModel() {

    val pagingData = getStoreItemUseCase().cachedIn(viewModelScope)
}