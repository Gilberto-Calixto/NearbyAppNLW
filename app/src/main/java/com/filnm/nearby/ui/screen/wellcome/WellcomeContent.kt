package com.filnm.nearby.ui.screen.wellcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filnm.nearby.R
import com.filnm.nearby.ui.theme.Typography

@Composable
fun WellcomeContent(modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text( text = "Veja como funciona", style = Typography.headlineSmall)

        WellcomeHowItWorksTip(
            modifier = Modifier.fillMaxWidth(),
            title = "Encontr o estebelecimento",
            subtitle = "Veja locais perto de você",
            iconRes = R.drawable.ic_map_pin
        )

        WellcomeHowItWorksTip(
            modifier = modifier.fillMaxWidth(),
            title = "Ative o cupon com QRcode",
            subtitle = "escaneie o código no estabelecimento para obeter benficios",
            iconRes = R.drawable.ic_qrcode
        )

        WellcomeHowItWorksTip(
            modifier = Modifier.fillMaxWidth(),
            title = "Garanta vantagens perto de você",
            subtitle = "Ative os cupons onde estiver",
            iconRes = R.drawable.ic_map_pin
        )
    }


}

@Preview
@Composable
private fun WellcomeContentPreview() {
    WellcomeContent()
}