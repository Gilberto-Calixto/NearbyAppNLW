package com.filnm.nearby.ui.screen.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filnm.nearby.core.network.RemoteDataSource
import com.filnm.nearby.data.model.NearbyCategory
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() { //Classe de viewmodel

    private val _UiState = MutableStateFlow(HomeUiState()) //Estado inicial mutavel e privado
    val uiState: StateFlow<HomeUiState> = _UiState.asStateFlow() //Estado atual imutavel e visivel para a UI

//Metodos para expor os dados para a UI
    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnFetchCategories -> fetchCategories()
            is HomeUiEvent.OnFetchMarkets -> fetchMarkets(categoryId = event.categoryId)
        }
    }

//Metodos privados
    //Buscar os dados das categorias e retornar o resultado para o uistate
    private fun fetchCategories() = viewModelScope.launch{ //Inicia uma corrotina no escopo do viewModel
        _UiState.update { currentUiState -> //Atualiza o estado atual da UI
            RemoteDataSource.getCategories().fold( //Faz a requisição para o servidor
                onSuccess = { categories -> //Se a requisição for bem sucedida
                    currentUiState.copy(categories = categories) //Retorna o estado atual da UI com as categorias
                },
                onFailure = { _ -> //Se a requisição falhar
                    currentUiState.copy(
                        categories = emptyList()
                    )
                }
            )

        }
    }

    //Buscar categoria por id
    private fun fetchMarkets(categoryId: String) = viewModelScope.launch{
        _UiState.update { currentUiState ->
            RemoteDataSource.getMarkets(categoryId).fold(
                onSuccess = { markets ->
                    currentUiState.copy(
                        markets = markets,
                        marketLocation = markets.map { market ->
                            LatLng(market.latitude, market.longitude)
                        }
                    )
                },
                onFailure = { _ ->
                    currentUiState.copy(
                        markets = emptyList(),
                        marketLocation = emptyList()
                    )
                }
            )
        }
    }

}