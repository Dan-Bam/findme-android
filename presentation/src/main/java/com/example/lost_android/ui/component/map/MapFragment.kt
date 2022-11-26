package com.example.lost_android.ui.component.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.ui.component.map.model.MapData
import com.example.lost_android.util.checkPermission
import com.example.lost_android.util.getBitmapFromVectorDrawable
import com.example.lost_android.viewmodel.MapViewModel
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
    private val mapViewModel by activityViewModels<MapViewModel>()
    private lateinit var mMap: GoogleMap
    private lateinit var currentLatLng: LatLng
    private lateinit var clusterManager: ClusterManager<MapData>

    override fun createView() {
        mapViewModel.findAll()
        binding.map.apply {
            onCreate(savedInstanceState)
            getMapAsync(this@MapFragment)
        }
        if (checkPermission(requireActivity(), listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))) {
            val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location == null) {
                Toast.makeText(context, "위치를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                currentLatLng = LatLng(37.5662952,126.97794509999994)
            } else {
                currentLatLng = LatLng(location.latitude, location.longitude)
            }
        }
    }

    private fun observeMap() = mapViewModel.mapData.observe(this) {
        it.forEach {
            clusterManager.addItem(MapData(it.latitude.toDouble(), it.longitude.toDouble(), it.title, it.description))
        }
        mMap.setOnCameraIdleListener(clusterManager)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        mMap.apply {
            clusterManager = ClusterManager<MapData>(requireContext(), mMap)
            addMarker(MarkerOptions().position(currentLatLng).icon(BitmapDescriptorFactory.fromBitmap(
                getBitmapFromVectorDrawable(requireContext(), R.drawable.ic_my_location)!!
            )))
            moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 17f))
            setMinZoomPreference(8f)
            setMaxZoomPreference(17f)
            setOnCameraIdleListener(clusterManager)
        }
        observeMap()
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