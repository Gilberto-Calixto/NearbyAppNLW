package com.filnm.nearby.ui.screen.HomeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.filnm.nearby.R
import com.filnm.nearby.data.model.Market
import com.filnm.nearby.data.model.mock.mockUserLocation
import com.filnm.nearby.ui.components.NearbyMaps
import com.filnm.nearby.ui.components.category.NearbyCategoryFilterChiplist
import com.filnm.nearby.ui.components.market.NearbyMarketCardList
import com.filnm.nearby.ui.theme.Gray100
import com.filnm.nearby.util.findNorthweastPoint
import com.filnm.nearby.util.findSouthweastPoint
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.currentCameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    onNavigationToMarketDetails: (Market) -> Unit
) {

    val context = LocalContext.current
    val density = LocalDensity.current

    val cameraPositionState = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(mockUserLocation, 13f)
    }
    val coroutineScope = rememberCoroutineScope()

    val bottomSheetState = rememberBottomSheetScaffoldState()
    var isBottomSheetOpened by remember { mutableStateOf(true) }

    val configuration = LocalConfiguration.current

    LaunchedEffect(true) {
        onEvent(HomeUiEvent.OnFetchCategories)
    }

    if ( isBottomSheetOpened)
        BottomSheetScaffold(
            modifier = Modifier,
            scaffoldState = bottomSheetState,
            sheetContainerColor = Gray100,
            sheetPeekHeight = configuration.screenHeightDp.dp * 0.5f,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {

                if (!uiState.markets.isNullOrEmpty())
                    NearbyMarketCardList(
                        modifier = modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        markets = uiState.markets,
                        onMarketClick = { selectedMarket ->
                            onNavigationToMarketDetails(selectedMarket)

                        }
                    )






            },
            content = {

                Box( modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = it.calculateBottomPadding().minus(8.dp)) //ajustar o bottomChip com bordas superiores
                ) {
                    NearbyMaps(uiState = uiState)

                    if (!uiState.categories.isNullOrEmpty())
                        NearbyCategoryFilterChiplist(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .align(Alignment.TopStart),
                            categories = uiState.categories,
                            onSelectedCategoryChagend = { selectedId ->
                                onEvent(HomeUiEvent.OnFetchMarkets(categoryId = selectedId.id))
                            }
                        )
                }


            }
        )

}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(
        onNavigationToMarketDetails = { },
        onEvent = { },
        uiState = HomeUiState()
    )

}