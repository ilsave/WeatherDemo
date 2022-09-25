package ru.gushchin.feature_detail.domain

import android.location.Location

interface LocationTraker {
    suspend fun getCurrentLocation(): Location?
}