package com.filnm.nearby.ui.screen.wellcome

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filnm.nearby.R

@Composable
fun WellcomeScreen(
    modifier: Modifier = Modifier,
    text: String? = null,
    @DrawableRes icon: Int? = null
) {

    Button(
        onClick = {  },
        modifier = modifier
            .heightIn(max = 48.dp, min = 24.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        icon?.let {
            Icon(
                painter = painterResource(id = it ),
                contentDescription = "Icone do botão"
            )
        }
        text?.let { Text(text = text.uppercase(), style = typography.bodyLarge) }

        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Preview
@Composable
private fun WellcomeScreenPreview() {
    WellcomeScreen( modifier = Modifier.fillMaxWidth() ,text = "Mind", icon = R.drawable.ic_launcher_foreground)
}

@Preview
@Composable
private fun WellcomeScreenPreview2() {
    WellcomeScreen(modifier = Modifier.fillMaxWidth() ,text = "Confirmar")
}