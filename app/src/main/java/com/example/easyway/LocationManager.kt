package com.example.easyway


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng

@Suppress("DEPRECATION")
class LocationManager(private val context: Context) {


    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private var locationCallback: LocationCallback? = null

    @SuppressLint("MissingPermission")
    fun startLocationUpdates(onLocationUpdate: (LatLng) -> Unit) {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000L).apply {
            setMinUpdateDistanceMeters(0f)
            setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
            setWaitForAccurateLocation(true)
        }.build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.locations.firstOrNull()?.let { location ->
                    val latLng = LatLng(location.latitude, location.longitude)
                    Log.d("LocationManager", "Location update: $latLng")
                    onLocationUpdate(latLng)
                }
            }
        }

        val callback = locationCallback
        if (callback != null) {
            Log.d("callback", "not null")
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, callback, null)
        }
    }

    fun stopLocationUpdates() {
        locationCallback?.let { fusedLocationProviderClient.removeLocationUpdates(it) }
    }
}