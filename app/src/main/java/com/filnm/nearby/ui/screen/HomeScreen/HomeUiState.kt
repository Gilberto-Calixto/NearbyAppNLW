package com.filnm.nearby.ui.screen.HomeScreen

import com.filnm.nearby.data.model.Market
import com.filnm.nearby.data.model.NearbyCategory
import com.google.android.gms.maps.model.LatLng

data class HomeUiState(
    val categories: List<NearbyCategory>? = null,
    val markets: List<Market>? = null,
    val marketLocation: List<LatLng>? = null
)
