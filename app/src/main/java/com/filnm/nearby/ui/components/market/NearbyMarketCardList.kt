package com.filnm.nearby.ui.components.market

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filnm.nearby.data.model.Market
import com.filnm.nearby.data.model.mock.mockMarkets
import com.filnm.nearby.ui.theme.Typography

@Composable
fun NearbyMarketCardList(
    modifier: Modifier = Modifier,
    markets: List<Market>,
    onClick: (Market) -> Unit
) {

    LazyColumn(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item{
            Text(
                text = "Explore locais perto de você",
                style = Typography.bodyLarge
            )
        }

        items(items = markets, key = {it.id}) { market ->
            NearbyMarketCard(
                market = market,
                onClick = { onClick(it)}
            )
        }
    }

}

@Preview
@Composable
private fun NearbyMarketCardListPrev() {
    NearbyMarketCardList(
        markets = mockMarkets,
        onClick = {}
    )
}