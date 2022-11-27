package com.example.lost_android.ui.adapter

import android.content.Context
import com.example.lost_android.ui.component.map.model.MapData
import com.example.lost_android.util.getBitmapFromVectorDrawable
import com.example.presentation.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class ClusterAdapter(
    val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<MapData>
) : DefaultClusterRenderer<MapData>(context, map, clusterManager) {
    override fun onBeforeClusterItemRendered(item: MapData, markerOptions: MarkerOptions) {
        markerOptions.apply {
            icon(
                BitmapDescriptorFactory.fromBitmap(
                    getBitmapFromVectorDrawable(context, R.drawable.ic_pin)!!
                )
            )
            title(item.title)
            snippet(item.snippet)
            super.onBeforeClusterItemRendered(item, markerOptions)
        }
    }
}