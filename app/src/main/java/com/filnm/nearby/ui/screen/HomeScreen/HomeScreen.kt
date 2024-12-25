package com.filnm.nearby.ui.screen.HomeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filnm.nearby.data.model.Market
import com.filnm.nearby.data.model.mock.categories
import com.filnm.nearby.data.model.mock.mockMarkets
import com.filnm.nearby.ui.components.category.NearbyCategoryFilterChiplist
import com.filnm.nearby.ui.components.market.NearbyMarketCardList
import com.filnm.nearby.ui.theme.Gray100
import com.google.maps.android.compose.GoogleMap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigationToMarketDetails: (Market) -> Unit
) {


    val bottomSheetState = rememberBottomSheetScaffoldState()
    var isBottomSheetOpened by remember { mutableStateOf(true) }

    val configuration = LocalConfiguration.current

    if ( isBottomSheetOpened)
        BottomSheetScaffold(
            modifier = Modifier,
            scaffoldState = bottomSheetState,
            sheetContainerColor = Gray100,
            sheetPeekHeight = configuration.screenHeightDp.dp * 0.5f,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {




                /*NearbyMarketCardList(
                    modifier = modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    markets = mockMarkets,
                    onMarketClick = { selectedMarket ->
                        onNavigationToMarketDetails(selectedMarket)

                    }
                )*/
            },
            content = {
                Box( modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
                ) {
                    GoogleMap(modifier = Modifier.matchParentSize())

                    NearbyCategoryFilterChiplist(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                            .align(Alignment.TopStart),
                        categories = categories,
                        onSelectedCategoryChagend = { }
                    )

                }
            }
        )

}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(onNavigationToMarketDetails = { } )

}