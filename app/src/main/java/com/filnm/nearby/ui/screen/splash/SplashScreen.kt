package com.filnm.nearby.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.filnm.nearby.R
import com.filnm.nearby.ui.theme.GreenExtraLight
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavegateToWellcome: () -> Unit
) {
    /*navigationToWellcome é uma função que vamos
            fazer a transferência de tela, da splash para ela*/

    LaunchedEffect(key1 = Unit) { // Criar um delay para a transição da tela
        delay(3_000)// 3 segundos
        onNavegateToWellcome() // Navegar para a tela Home
    }

    Box(
        modifier = modifier
            .background(GreenExtraLight)
            .fillMaxSize()
    ) {

        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.img_logo_logo_logo_text),
            contentDescription = "Imagem da logo"
        )

        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.bg_splash_screen),
            contentDescription = "Imagem do fundo"
        )

    }

}

@Preview
@Composable
private fun SplachScreenPreview() {
    SplashScreen( onNavegateToWellcome = { })
}






