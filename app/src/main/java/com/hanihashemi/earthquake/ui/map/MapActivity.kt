package com.hanihashemi.earthquake.ui.map

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import com.hanihashemi.earthquake.data.network.Status
import com.hanihashemi.earthquake.data.repository.EventRepository
import com.hanihashemi.earthquake.model.Feature
import com.hanihashemi.earthquake.ui.map.adapter.EventAdapter
import kotlinx.android.synthetic.main.maps_activity.*
import timber.log.Timber


class MapActivity : BaseTransparentActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var viewModel: MapViewModel
    private var page = 1
    private var isLoading = false
    private val features = mutableListOf<Feature>()

    override val layoutResource
        get() = R.layout.maps_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initMap()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = EventAdapter(features)
        val layoutManager = LinearLayoutManager(this)
        rclEarthQuakeList.adapter = adapter
        rclEarthQuakeList.layoutManager = layoutManager
//        rclEarthQuakeList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                val visibleItemCount = layoutManager.childCount
//                val totalItemCount = layoutManager.itemCount
//                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//
//                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
//                        firstVisibleItemPosition >= 0 &&
//                        !isLoading) {
//                    page += 10
//                    viewModel.nextPage()
//                }
//            }
//        })
        subscribeUi(adapter)
        viewModel.nextPage()
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
        viewModel.features.observe(this, Observer { resource ->
            if (resource?.data != null) {
                features.addAll(resource.data.features.toTypedArray())
                adapter.notifyDataSetChanged()
            }
            isLoading = resource?.status == Status.LOADING
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
