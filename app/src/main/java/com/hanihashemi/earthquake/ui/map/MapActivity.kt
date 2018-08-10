package com.hanihashemi.earthquake.ui.map

import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.hanihashemi.earthquake.R
import com.hanihashemi.earthquake.base.BaseTransparentActivity
import timber.log.Timber

class MapActivity : BaseTransparentActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override val layoutResource
        get() = R.layout.activity_maps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        if (!googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.google_map)))
            Timber.d("====> Style parsing failed.");

        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
