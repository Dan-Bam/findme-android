package com.example.lost_android.ui.component.map

import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.ui.component.map.model.MapData
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager

class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var currentLatLng: LatLng

    override fun createView() {
        binding.map.apply {
            onCreate(savedInstanceState)
            getMapAsync(this@MapFragment)
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        val location = LatLng(37.5662952, 126.97794509999994)
        mMap.apply {
            val clusterManager = ClusterManager<MapData>(requireContext(), mMap)
//            addMarker(MarkerOptions().position(location).icon(BitmapDescriptorFactory.fromBitmap(
//                getBitmapFromVectorDrawable(requireContext(), R.drawable.ic_my_location)!!
//            )))
            setMinZoomPreference(8f)
            setMaxZoomPreference(17f)
            moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17f))
            setOnCameraIdleListener(clusterManager)
            for (i in 1..10) {
                val lat = location.latitude + (i / 2000f)
                val lng = location.longitude + (i / 2000f)
                clusterManager.addItem(MapData(lat, lng, "", ""))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.map.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.map.onLowMemory()
    }
}