package com.filnm.nearby.ui.screen.marketDetails

sealed class MarketDetailsUiEvent{
    data class OnFetchRules(val marketId: String): MarketDetailsUiEvent()
    data class OnFetchCoupon(val qrCodeontent: String): MarketDetailsUiEvent()
    data object OnResetCoupon: MarketDetailsUiEvent()
}