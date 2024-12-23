package com.filnm.nearby.ui.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filnm.nearby.R
import com.filnm.nearby.ui.theme.GreenBase

@Composable
fun NearbyButton( // O que esse componente vai ter?
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: Int? = null,
    onClick: () -> Unit
) {


        Button(
            modifier = modifier
                .heightIn(min = 36.dp),
            shape = RoundedCornerShape(9.dp),
            contentPadding = if (text == null && icon != null) PaddingValues(0.dp)
                else ButtonDefaults.ContentPadding, //Tira o preenchimento desnecessário
            colors = ButtonDefaults.buttonColors(containerColor = GreenBase),
            onClick = { onClick }
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp), //Espaçamento horizontal
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let { icone ->
                    Icon(
                        painter = painterResource(id = icone), contentDescription = "Icone do btn"
                    )
                }
                text?.let { Text(text = text.uppercase()) }
            }
        }

}

@Preview
@Composable
private fun NearbyButtonPreview() {
    NearbyButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
        text = "Bettu",
        icon = R.drawable.ic_scan
    )
}

@Preview
@Composable
private fun NearbyButtonPrev2() {
    NearbyButton(
        modifier = Modifier,
        onClick = {},
        icon = R.drawable.ic_arrow_left
    )
}