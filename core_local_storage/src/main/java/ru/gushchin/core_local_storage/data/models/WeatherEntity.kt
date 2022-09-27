package ru.gushchin.core_local_storage.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherEntity(
    val city: Long,
    val icon: String,
    val temperature: Double,
    val speed: Double,
    val description: String
) {
    @PrimaryKey (autoGenerate = true)
    var id: Long = 0

    constructor(
        id: Long,
        city: Long,
        icon: String,
        temperature: Double,
        speed: Double,
        description: String
    ) : this(city, icon, temperature, speed, description) {
        this.id = id
    }


}