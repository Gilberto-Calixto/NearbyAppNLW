package com.filnm.nearby.ui.screen.HomeScreen

sealed class HomeUiEvent {
    data object OnFetchCategories: HomeUiEvent()
    data class OnFetchMarkets(val categoryId: String): HomeUiEvent()
}