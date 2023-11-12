package com.example.easyway.ui.route

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.easyway.LocationManager
import com.example.easyway.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.Instant
import kotlin.math.roundToInt
import kotlin.random.Random


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RouteMapView(
    modifier: Modifier = Modifier,
) {
    //시간 기록용
    var startTime by remember { mutableStateOf(Instant.now()) } // 창이 시작하면 타이머 시작
    var isTimerRunning by remember { mutableStateOf(true) } // 타이머 실행 상태
    var currentTime by remember { mutableStateOf(startTime) }

    val currentLocation = remember { mutableStateOf<LatLng?>(null) }
    val pathPoints = remember { mutableStateOf(listOf<LatLng>()) }
    val markers = remember { mutableStateOf(listOf<LatLng>()) }
    val isTracking = remember { mutableStateOf(true) }
    val routeAdded = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isTimerRunning) {
        while (isTimerRunning) {
            delay(1000) // 1밀리초마다 갱신
            currentTime = Instant.now()
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        RealTimeLocationComponent(currentLocation, pathPoints, markers, isTracking.value)

        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            //기록창
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(120.dp)
                    .background(
                        color = Color.White, shape = RoundedCornerShape(10.dp)
                    )
                    .padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(
                        4.dp, Alignment.CenterHorizontally
                    ), verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_timer_24),
                        contentDescription = null
                    )

                    val elapsedTime = Duration.between(startTime, currentTime)
                    val minutes = elapsedTime.toMinutes()
                    val seconds = elapsedTime.seconds % 60
                    Text(
                        text = "${minutes}\'\t${seconds}\"",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                )

                Row(
                    modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(
                        4.dp, Alignment.CenterHorizontally
                    ), verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_directions_walk_24),
                        contentDescription = null
                    )
                    val elapsedTime = Duration.between(startTime, currentTime)
                    val meter = elapsedTime.seconds + ((Random.nextFloat() * 10).roundToInt() / 10)

                    Text(
                        text = "${meter}M",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        isTimerRunning = !isTimerRunning
                        isTracking.value = false
                    },
                    modifier = Modifier
                        .size(width = 200.dp, height = 54.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.sub_0),
                        contentColor = Color.White
                    ),

                    ) {
                    Text(
                        text = "기록 종료 하기",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                Button(
                    onClick = {
                        currentLocation.value?.let { location ->
                            markers.value = markers.value + location
                        }
                        routeAdded.value = !routeAdded.value
                    },
                    modifier = Modifier
                        .size(54.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.sub_0),
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_add_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            }
        }

        if (!isTracking.value) {
            Log.d("리워드 창", "떠야댐")
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(IntrinsicSize.Min)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 0.5.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 30.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "경로 추가 완료",
                    color = colorResource(id = R.color.main_0),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )

                Text(
                    text = "관리자 승인 후\n리워드 834원 적립 예정",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = {
                        isTracking.value = !isTracking.value
                    },
                    modifier = Modifier
                        .size(width = 200.dp, height = 54.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.main_0),
                        contentColor = Color.White
                    ),

                    ) {
                    Text(
                        text = "확인",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
fun RealTimeLocationComponent(
    currentLocation: MutableState<LatLng?>,
    pathPoints: MutableState<List<LatLng>>,
    markers: MutableState<List<LatLng>>,
    isTracking: Boolean
) {
    val context = LocalContext.current
    val locationManager = remember { LocationManager(context) }

    DisposableEffect(Unit) {
        locationManager.startLocationUpdates { location ->
            currentLocation.value = location
            pathPoints.value = pathPoints.value + location
        }

        onDispose {
            locationManager.stopLocationUpdates()
        }
    }
    if (!isTracking){
        locationManager.stopLocationUpdates()
    }

    GoogleMapComponent(currentLocation.value, pathPoints.value, markers.value)
}

@Composable
fun GoogleMapComponent(currentLocation: LatLng?, pathPoints: List<LatLng>, markers: List<LatLng>) {
    val mapView = rememberMapViewWithLifecycle()
    val color = colorResource(id = R.color.main_0).toArgb()
    var currentZoom by remember { mutableFloatStateOf(100f) }
    val zoomLevel = 19f

    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize()
    ) { mapView ->
        mapView.getMapAsync { googleMap ->
            googleMap.setOnCameraMoveListener {
                currentZoom = googleMap.cameraPosition.zoom
            }

            googleMap.clear() // 이전 마커 및 경로 제거

            // 현재 위치 마커 추가
            currentLocation?.let { location ->
                googleMap.addMarker(MarkerOptions().position(location))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
            }

            // 경로 그리기
            if (pathPoints.size > 1) {
                val polylineOptions = PolylineOptions()
                    .addAll(pathPoints)
                    .color(color)
                    .width(8f)
                googleMap.addPolyline(polylineOptions)
            }

            //마커 추가
            markers.forEach { markerLocation ->
                val markerOptions = MarkerOptions()
                    .position(markerLocation)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                googleMap.addMarker(markerOptions)
            }
        }
    }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            onCreate(Bundle())
        }
    }
    // 생명주기 처리
    DisposableEffect(mapView) {
        mapView.onStart()
        onDispose {
            mapView.onStop()
        }
    }
    return mapView
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun RouteMapViewPreview() {
    RouteMapView()
}