 package com.filnm.nearby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.filnm.nearby.data.model.Market
import com.filnm.nearby.ui.screen.HomeScreen.HomeScreen
import com.filnm.nearby.ui.screen.MarketDetailsScreen
import com.filnm.nearby.ui.screen.route.Home
import com.filnm.nearby.ui.screen.route.Splash
import com.filnm.nearby.ui.screen.route.Wellcome
import com.filnm.nearby.ui.screen.splash.SplashScreen
import com.filnm.nearby.ui.screen.wellcome.WellcomeScreen
import com.filnm.nearby.ui.theme.NearbyTheme

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyTheme {

                val navController = rememberNavController()
                NavHost( // Componente que vai controlar a navegação
                    navController = navController,
                    startDestination = Splash // Tela inicial
                ) {
                    composable<Splash>{ // Componente que vai desenhar a tela
                            SplashScreen(
                                onNavegateToWellcome = { navController.navigate(Wellcome) } // Navegar para a tela Home
                            )
                    }
                    composable<Wellcome>{
                        WellcomeScreen(
                            onNavigationToHome = {
                                navController.navigate(Home)
                            }
                        )
                    }
                    composable<Home> {
                        HomeScreen(
                            onNavigationToMarketDetails = { selectedMarket ->
                                navController.navigate(selectedMarket)
                            }
                        )
                    }
                    composable<Market>{
                        val selectedMarket = it.toRoute<Market>()

                        MarketDetailsScreen(
                            market = selectedMarket,
                            onNavigationBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

