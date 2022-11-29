package com.example.lost_android.ui.component.entry

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lost_android.ui.adapter.EntryAddressAdapter
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.util.getBitmapFromVectorDrawable
import com.example.lost_android.util.setOnTextChanged
import com.example.lost_android.viewmodel.EntryViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEntryMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class EntryMapFragment : BaseFragment<FragmentEntryMapBinding>(R.layout.fragment_entry_map),
    OnMapReadyCallback {
    private val entryViewModel by activityViewModels<EntryViewModel>()
    private lateinit var mMap: GoogleMap
    private var currentMarker: Marker? = null
    private var currentLatLng: LatLng? = null
    private var chooseMarker: Marker? = null
    private lateinit var adapter: EntryAddressAdapter

    override fun createView() {
        binding.entryMapFragment = this
        binding.map.apply {
            onCreate(savedInstanceState)
            getMapAsync(this@EntryMapFragment)
        }
        initList()
        searchAddress()
    }

    private fun initList() {
        adapter = EntryAddressAdapter {
            entryViewModel.getLanLng(it.road)
        }
        binding.addressList.apply {
            adapter = this@EntryMapFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
        entryViewModel.currentAddress.observe(this) {
            binding.addressList.visibility = View.GONE
            if (chooseMarker != null) {
                chooseMarker!!.remove()
            }
            chooseMarker = mMap.addMarker(
                MarkerOptions().position(it.latLng).icon(
                    BitmapDescriptorFactory.fromBitmap(
                        getBitmapFromVectorDrawable(
                            requireContext(),
                            R.drawable.ic_pin
                        )!!
                    )
                )
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLng(it.latLng))
            binding.selectBtn.isEnabled = true
            binding.selectBtn.isActivated = true
        }
    }

    private fun searchAddress() {
        binding.searchAreaTxt.setOnTextChanged { p0, _, _, _ ->
            binding.addressList.visibility = View.VISIBLE
            entryViewModel.getAddress(p0.toString())
        }
        entryViewModel.addressInfo.observe(this) {
            adapter.submitList(it)
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.selectBtn -> requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.entryContainer, EntryFragment()).commit()
            R.id.myLocationBtn -> {
                if (currentLatLng != null) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng!!))
                } else {
                    Toast.makeText(context, "위치권한을 허락해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        this.checkPermission()
        mMap = p0
        mMap.apply {
            setMinZoomPreference(8f)
            setMaxZoomPreference(17f)
            setOnMapClickListener {
                if (chooseMarker != null) {
                    chooseMarker!!.remove()
                }
                chooseMarker = this.addMarker(
                    MarkerOptions().position(it).icon(
                        BitmapDescriptorFactory.fromBitmap(
                            getBitmapFromVectorDrawable(
                                requireContext(),
                                R.drawable.ic_pin
                            )!!
                        )
                    )
                )
                entryViewModel.getAddress(LatLng(it.latitude, it.longitude))
                binding.selectBtn.isEnabled = true
                binding.selectBtn.isActivated = true
            }
            if (entryViewModel.currentAddress.value != null) {
                chooseMarker = this.addMarker(
                    MarkerOptions().position(entryViewModel.currentAddress.value!!.latLng).icon(
                        BitmapDescriptorFactory.fromBitmap(
                            getBitmapFromVectorDrawable(
                                requireContext(),
                                R.drawable.ic_pin
                            )!!
                        )
                    )
                )
                moveCamera(CameraUpdateFactory.newLatLngZoom(entryViewModel.currentAddress.value!!.latLng, 17f))
            }
        }
        initMap()
    }

    private fun initMap() = mMap.apply {
        if (currentMarker != null) {
            currentMarker!!.remove()
        }
        currentMarker = this.addMarker(
            MarkerOptions().position(currentLatLng ?: LatLng(37.5662952, 126.97794509999994)).icon(
                BitmapDescriptorFactory.fromBitmap(
                    getBitmapFromVectorDrawable(requireContext(), R.drawable.ic_my_location)!!
                )
            )
        )!!
        if (entryViewModel.currentAddress.value == null) {
            moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    currentLatLng ?: LatLng(
                        37.5662952,
                        126.97794509999994
                    ), 17f
                )
            )
        }
    }

    private fun checkPermission() {
        if (com.example.lost_android.util.checkPermission(
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
            ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
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