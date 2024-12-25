package com.filnm.nearby.ui.screen.marketDetails

import com.filnm.nearby.data.model.Rule

data class MarketDetailsUiState(
    val rules: List<Rule>? = null,
    val coupon: String? = null
)
