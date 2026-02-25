package com.example.kittystore.data.mapper

import com.example.kittystore.data.dto.StoreItemDto
import com.example.kittystore.domain.model.StoreItem

fun StoreItemDto.toDomain() = StoreItem(
    id = id,
    name = name,
    imageUrl = imageUrl
)
