package ru.gushchin.core_local_storage.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityEntity(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val isFavourite: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor(
        id: Long,
        name: String,
        latitude: Double,
        longitude: Double,
        isFavourite: Boolean
    ) : this(name, latitude, longitude, isFavourite) {
        this.id = id
    }

    fun isTheSameCity(other: CityEntity) =
        (name == other.name) && (latitude == other.latitude) && (longitude == other.longitude)
}