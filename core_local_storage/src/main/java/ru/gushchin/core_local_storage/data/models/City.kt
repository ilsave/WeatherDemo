package ru.gushchin.core_local_storage.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey
    val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double
)