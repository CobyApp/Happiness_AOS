package com.coby.happiness.domain.model

import java.util.UUID

data class PlaceModel(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val latitude: Double,
    val longitude: Double
)