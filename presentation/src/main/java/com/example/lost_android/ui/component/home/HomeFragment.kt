package com.example.lost_android.ui.component.home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lost_android.ui.adapter.LostAdapter
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.ui.component.detail.DetailActivity
import com.example.lost_android.util.checkPermission
import com.example.lost_android.viewmodel.HomeViewModel
import com.example.lost_android.viewmodel.MainViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeBinding
import com.google.android.gms.maps.model.LatLng

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private var currentLatLng: LatLng? = null
    private lateinit var adapter: LostAdapter

    override fun onResume() {
        super.onResume()
        if (homeViewModel.currentArea.value != null) {
            homeViewModel.search()
        }
    }

    override fun createView() {
        mainViewModel.setTitle(getString(R.string.all))
        homeViewModel.search()
        observeLost()
        initLostList()
        click()
        checkPermission()
    }

    private fun initLostList() = binding.lostList.apply {
        this@HomeFragment.adapter = LostAdapter(requireContext()) {
            requireActivity().startActivity(
                Intent(
                    context,
                    DetailActivity::class.java
                ).putExtra("type", getString(R.string.editLost)).putExtra("lostId", it.id)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }
        adapter = this@HomeFragment.adapter
        layoutManager = GridLayoutManager(context, 2)
    }

    private fun observeLost() = homeViewModel.lostList.observe(this) {
        adapter.submitList(it)
    }

    private fun click() {
        binding.entryBtn.setOnClickListener {
            EntryDialog().show(
                requireActivity().supportFragmentManager,
                "RegisterDialog"
            )
        }
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
            ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        currentLatLng = if (location == null) {
            Toast.makeText(context, "위치를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
            LatLng(37.5662952, 126.97794509999994)
        } else {
            LatLng(location.latitude, location.longitude)
        }
        homeViewModel.searchSetArea(currentLatLng!!)
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
            }
        }
    }
}