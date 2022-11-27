package com.example.lost_android.ui.component.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.lost_android.ui.adapter.ClusterAdapter
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.ui.component.map.model.MapData
import com.example.lost_android.util.checkPermission
import com.example.lost_android.util.getBitmapFromVectorDrawable
import com.example.lost_android.viewmodel.MainViewModel
import com.example.lost_android.viewmodel.MapViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener


class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map), OnMapReadyCallback {
    private val mapViewModel by activityViewModels<MapViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var mMap: GoogleMap
    private var currentMarker: Marker? = null
    private var currentLatLng: LatLng? = null
    private lateinit var clusterManager: ClusterManager<MapData>

    override fun createView() {
        binding.map.apply {
            onCreate(savedInstanceState)
            getMapAsync(this@MapFragment)
        }
        binding.mapFragment = this
        mapViewModel.findAll()
        mainViewModel.setTitle(getString(R.string.findItem))
    }

    fun click(view: View) {
        when (view.id) {
            R.id.myLocationBtn -> {
                if (currentLatLng != null) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng!!))
                } else {
                    Toast.makeText(context, "위치권한을 허락해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeMap() = mapViewModel.mapData.observe(this) {
        it.forEach {
            clusterManager.addItem(
                MapData(
                    it.lostImages[0],
                    it.latitude.toDouble(),
                    it.longitude.toDouble(),
                    it.title,
                    it.description,
                    it.place,
                    it.tags
                )
            )
        }
        mMap.setOnCameraIdleListener(clusterManager)
    }

    override fun onMapReady(p0: GoogleMap) {
        this.checkPermission()
        mMap = p0
        mMap.apply {
            clusterManager = ClusterManager<MapData>(requireContext(), mMap)
            clusterManager.renderer = ClusterAdapter(requireContext(), mMap, clusterManager)
            clusterManager.setOnClusterItemClickListener { it ->
                DetailDialog(requireContext(), it).show()
                false
            }
            setMinZoomPreference(8f)
            setMaxZoomPreference(17f)
            setOnCameraIdleListener(clusterManager)
        }
        initMap()
        observeMap()
    }

    private fun initMap() = mMap.apply {
        if (currentMarker != null) {
            currentMarker!!.remove()
        }
        currentMarker = this.addMarker(MarkerOptions().position(currentLatLng ?: LatLng(37.5662952,126.97794509999994)).icon(BitmapDescriptorFactory.fromBitmap(
            getBitmapFromVectorDrawable(requireContext(), R.drawable.ic_my_location)!!
        )))!!
        moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                currentLatLng ?: LatLng(
                    37.5662952,
                    126.97794509999994
                ), 17f
            )
        )
    }

    private fun checkPermission() {
        if (checkPermission(
                this,
                listOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        ) {
            getLocation()
        }
    }

    private fun getLocation() {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        currentLatLng = if (location == null) {
            Toast.makeText(context, "위치를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
            LatLng(37.5662952, 126.97794509999994)
        } else {
            LatLng(location.latitude, location.longitude)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        grantResults.forEach {
            if (it == PackageManager.PERMISSION_GRANTED) {
                getLocation()
                initMap()
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