package com.coby.happiness.domain.model

import java.io.Serializable

data class LocationModel(
    val lat: Double,
    val lon: Double
) : Serializable