package com.filnm.nearby.ui.screen.marketDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filnm.nearby.core.network.RemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarketDetailsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MarketDetailsUiState())
    val uiState: StateFlow<MarketDetailsUiState> = _uiState.asStateFlow()


    fun onEvent(event: MarketDetailsUiEvent) {
        when (event) {
            is MarketDetailsUiEvent.OnFetchRules -> fetchRules(event.marketId)
            is MarketDetailsUiEvent.OnFetchCoupon -> fetchCoupon(event.qrCodeontent)
            MarketDetailsUiEvent.OnResetCoupon -> resetCoupon()
        }
    }


    private fun fetchCoupon(qrCodeContent: String) = viewModelScope.launch{
        RemoteDataSource.patchCoupon(marketId = qrCodeContent)
            .onSuccess { coupon ->
                _uiState.update { currentUiState ->
                    currentUiState.copy(
                        coupon = coupon.coupon
                    )
                }
            }
            .onSuccess {
                _uiState.update { currentUiState ->
                    currentUiState.copy(coupon = "")
                }
            }
    }
    
    private fun fetchRules(marketId: String) = viewModelScope.launch{
        RemoteDataSource.getMarketDetails(marketId = marketId)
            .onSuccess { marketDetails ->
                _uiState.update { currentUiState ->
                    currentUiState.copy( rules = marketDetails.rules)
                }
            }
            .onFailure {
                _uiState.update { currentUiState ->
                    currentUiState.copy(
                        rules = emptyList()
                    )
                }
            }
    }

    private fun resetCoupon() {
        _uiState.update { currentUiState ->
            currentUiState.copy(
                coupon = null
            )
        }
    }

}