package ru.gushchin.feature_detail.data

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}