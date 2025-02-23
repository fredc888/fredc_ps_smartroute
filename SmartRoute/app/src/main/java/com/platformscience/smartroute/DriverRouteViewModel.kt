package com.platformscience.smartroute

import androidx.lifecycle.ViewModel
import com.platformscience.smartroute.data.RouteResults
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class DriverRouteUiState(val routeResults: RouteResults?=null) {

}
class DriverRouteViewModel : ViewModel(){
    val _uiState = MutableStateFlow(DriverRouteUiState())
    val uiState: StateFlow<DriverRouteUiState> = _uiState.asStateFlow()

    // Handle business logic
    fun updateRoutes(routeResults: RouteResults?=null) {
        _uiState.update { currentState ->
            currentState.copy(routeResults)
        }
    }
}