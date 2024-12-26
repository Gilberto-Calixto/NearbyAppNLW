package com.filnm.nearby.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.filnm.nearby.R
import com.filnm.nearby.data.model.mock.mockUserLocation
import com.filnm.nearby.ui.screen.HomeScreen.HomeUiState
import com.filnm.nearby.util.findNorthweastPoint
import com.filnm.nearby.util.findSouthweastPoint
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import kotlin.math.roundToInt

@Composable
fun NearbyMaps(modifier: Modifier = Modifier, uiState: HomeUiState) {

    val context = LocalContext.current
    val density = LocalDensity.current

    val cameraPositionState = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(mockUserLocation, 13f)
    }
    val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current

    val uiSettings by remember { //Configurações do mapa
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }




    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState, // Posição da camera
        uiSettings = uiSettings //Configurações do mapa
    ) {
        context.getDrawable(R.drawable.ic_user_location)?.let{
            Marker(
                state = MarkerState(position = mockUserLocation),
                icon = BitmapDescriptorFactory.fromBitmap(
                    it.toBitmap(
                        width = density.run { 72.dp.toPx() }.roundToInt(),
                        height = density.run { 72.dp.toPx() }.roundToInt()
                    )
                )
            )
        }

        if (!uiState.markets.isNullOrEmpty())
            context.getDrawable(R.drawable.img_pin)?.let {
                uiState.marketLocation?.toImmutableList()
                    ?.forEachIndexed { index, location ->
                        Marker(
                            state = MarkerState(position = location),
                            icon = BitmapDescriptorFactory.fromBitmap(
                                it.toBitmap(
                                    width = density.run { 36.dp.toPx() }.roundToInt(),
                                    height = density.run { 36.dp.toPx() }.roundToInt()
                                )
                            ),
                            title = uiState.markets[index].name
                        )
                    }.also {
                        coroutineScope.launch{
                            val allMarks = uiState.marketLocation?.plus(
                                mockUserLocation
                            )

                            val southWestPoint = findSouthweastPoint(points = allMarks.orEmpty())
                            val northeastPoint = findNorthweastPoint(points = allMarks.orEmpty())

                            val cameraUpdate = CameraUpdateFactory.newCameraPosition(
                                CameraPosition(
                                    LatLng(
                                        (southWestPoint.latitude + northeastPoint.latitude) / 2,
                                        (northeastPoint.longitude + northeastPoint.longitude) / 2,
                                    ),
                                    13f,
                                    0f,0f
                                )
                            )
                            delay(timeMillis = 200)
                            cameraPositionState.animate(cameraUpdate, durationMs = 500)
                        }
                    }
            }

    }






}