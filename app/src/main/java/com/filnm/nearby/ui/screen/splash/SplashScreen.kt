package com.filnm.nearby.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filnm.nearby.R
import com.filnm.nearby.ui.theme.Gray600

@Composable
fun SplashScreen(modifier: Modifier = Modifier, wellcomeScreen: () -> Unit = {}) {

    Box(
        modifier = Modifier
            .background(Gray600)
            .fillMaxSize()
    ) {

        Image(
            modifier = modifier
                .align(Alignment.TopCenter)
                .height(60.dp)
                .padding(top = 20.dp),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Logo",
        )

        Spacer(modifier = Modifier.height(40.dp))


        Image(
            modifier = modifier.align(Alignment.Center),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Logo"
        )

        Button(
            onClick = { wellcomeScreen }
        ) { }

        Text(
           text = "Nearby",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Green,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        )
    }

}

@Preview
@Composable
private fun SplachScreenPreview() {
    SplashScreen()
}






