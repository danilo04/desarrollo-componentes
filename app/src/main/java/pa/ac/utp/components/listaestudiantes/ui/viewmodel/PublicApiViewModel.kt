package pa.ac.utp.components.listaestudiantes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pa.ac.utp.components.listaestudiantes.data.PublicApiRepository
import pa.ac.utp.components.listaestudiantes.data.dto.PublicApi
import javax.inject.Inject

@HiltViewModel
class PublicApiViewModel @Inject constructor(
    private val publicApiRepository: PublicApiRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.PublicApiState(publicApiRepository.getRandomPublicApi())
        }
    }


    sealed interface UiState {
        object Loading : UiState
        data class PublicApiState(val publicApi: PublicApi?) : UiState
    }
}
