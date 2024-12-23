package com.filnm.nearby.ui.screen.wellcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.filnm.nearby.R
import com.filnm.nearby.ui.theme.Typography

@Composable
fun WellcomeHeader(modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            modifier = modifier,
            painter = painterResource(id = R.drawable.img_logo_logo_icon),
            contentDescription = "Imagem cabeçario"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier,
            text = "Bem-vindo ao NearBy",
            style = Typography.headlineSmall
        )
        Text(
            text = "Tenha cupons de desconto perto de você",
            style = Typography.bodyLarge
        )


    }
}