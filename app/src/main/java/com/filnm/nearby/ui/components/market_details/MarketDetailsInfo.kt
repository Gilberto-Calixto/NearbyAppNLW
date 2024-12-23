package com.filnm.nearby.ui.components.market_details

import android.R.attr.text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filnm.nearby.data.model.Market
import com.filnm.nearby.data.model.mock.mockMarkets
import com.filnm.nearby.ui.theme.Gray400
import com.filnm.nearby.ui.theme.Typography
import com.filnm.nearby.R
import com.filnm.nearby.ui.theme.Gray500
import com.filnm.nearby.ui.theme.Gray600

@Composable
fun MarketDetailsInfos(modifier: Modifier = Modifier, market: Market) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = "Informações", style = Typography.headlineSmall, color = Gray400)

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_ticket),
                    tint = Gray600,
                    contentDescription = "Icone da tela de detalhes"
                )

                Text(
                    text = "${market.coupons} cupons disponiveis",
                    style = Typography.labelMedium,
                    color = Gray500
                )

            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_map_pin),
                    tint = Gray600,
                    contentDescription = "Icone da tela de detalhes"
                )

                Text(
                    text = market.address,
                    style = Typography.labelMedium,
                    color = Gray500
                )

            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_phone),
                    tint = Gray600,
                    contentDescription = "Icone da tela de detalhes"
                )

                Text(
                    text = market.phone,
                    style = Typography.labelMedium,
                    color = Gray500
                )

            }


        }

    }
}

@Preview
@Composable
private fun MarketDetailsInfosPreview() {
    MarketDetailsInfos(
        modifier = Modifier.fillMaxWidth(),
        market = mockMarkets.first()
    )
}