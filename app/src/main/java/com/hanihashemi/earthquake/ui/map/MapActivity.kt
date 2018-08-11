package com.hanihashemi.earthquake.ui.map

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
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
import com.hanihashemi.earthquake.data.db.AppDatabase
import com.hanihashemi.earthquake.data.network.Retrofit
import com.hanihashemi.earthquake.data.repository.EventRepository
import com.hanihashemi.earthquake.ui.map.adapter.EventAdapter
import kotlinx.android.synthetic.main.activity_maps.*
import timber.log.Timber

class MapActivity : BaseTransparentActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var viewModel: MapViewModel

    override val layoutResource
        get() = R.layout.activity_maps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initMap()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = EventAdapter()
        rclEarthQuakeList.adapter = adapter
        subscribeUi(adapter)
    }

    private fun initViewModel() {
        val db = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "database-name").build()

        viewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)
        viewModel.init(EventRepository(Retrofit.event(), db.featureDao()))
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun subscribeUi(adapter: EventAdapter) {
        viewModel.getEvents().observe(this, Observer { resource ->
            if (resource != null) adapter.submitList(resource.data?.features)
        })
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
