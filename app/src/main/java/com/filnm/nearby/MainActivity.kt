 package com.filnm.nearby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.filnm.nearby.data.model.Market
import com.filnm.nearby.ui.screen.HomeScreen.HomeScreen
import com.filnm.nearby.ui.screen.HomeScreen.HomeViewModel
import com.filnm.nearby.ui.screen.QRCodeScannerScreen
import com.filnm.nearby.ui.screen.marketDetails.MarketDetailsScreen
import com.filnm.nearby.ui.screen.marketDetails.MarketDetailsUiEvent
import com.filnm.nearby.ui.screen.marketDetails.MarketDetailsViewModel
import com.filnm.nearby.ui.screen.route.Home
import com.filnm.nearby.ui.screen.route.QRCodeScanner
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

                val homeViewModel by viewModels<HomeViewModel>()
                val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

                val marketdetailsviewmodel by viewModels<MarketDetailsViewModel>()
                val marketDetailsUiState by marketdetailsviewmodel.uiState.collectAsStateWithLifecycle()

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
                            },
                            uiState = homeUiState ,
                            onEvent = homeViewModel::onEvent
                        )
                    }
                    composable<Market>{
                        val selectedMarket = it.toRoute<Market>()

                        MarketDetailsScreen(
                            market = selectedMarket,
                            onNavigationBack = { navController.popBackStack() },
                            uiState = marketDetailsUiState,
                            onEvent = marketdetailsviewmodel::onEvent,
                            onNavigateToQRCodeScanner = { navController.navigate(QRCodeScanner)}
                        )
                    }
                    composable<QRCodeScanner>{
                        QRCodeScannerScreen(
                            onCompletScan = { qrCodeContent ->
                                if (qrCodeContent.isNotEmpty())
                                    marketdetailsviewmodel.onEvent(
                                        MarketDetailsUiEvent.OnFetchCoupon(
                                            qrCodeontent = qrCodeContent
                                        )
                                    )
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

