package com.example.lost_android.ui.component.map.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class MapData(
    val img: String,
    val Lat: Double,
    val Lng: Double,
    val mapTitle: String?,
    val description: String?,
    val place: String,
    val tags: List<String>
): ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(Lat, Lng)
    }

    override fun getTitle(): String? {
        return mapTitle
    }

    override fun getSnippet(): String? {
        return description
    }
}