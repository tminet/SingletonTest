package example.singletontest.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import example.singletontest.domain.usecase.IsAppThemeDarkModeUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isAppThemeDarkModeUseCase: IsAppThemeDarkModeUseCase
) : ViewModel() {
    var state by mutableStateOf(value = MainState())
        private set

    init {
        loadInitialConfig()
    }

    private fun loadInitialConfig() = viewModelScope.launch {
        isAppThemeDarkModeUseCase().collectLatest { isDark ->
            state = state.copy(
                isLoading = false,
                isAppThemeDarkMode = isDark
            )
        }
    }
}