package example.singletontest.presentation.screen_datastore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import example.singletontest.domain.usecase.IsAppThemeDarkModeUseCase
import example.singletontest.domain.usecase.UpdateAppThemeUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val isAppThemeDarkModeUseCase: IsAppThemeDarkModeUseCase,
    private val updateAppThemeUseCase: UpdateAppThemeUseCase
) : ViewModel() {
    var state by mutableStateOf(value = DataStoreState())

    init {
        getAppTheme()
    }

    fun onAction(action: DataStoreAction) {
        when (action) {
            is DataStoreAction.ChangeAppTheme -> updateAppTheme(darkMode = action.darkMode)
        }
    }

    private fun getAppTheme() = viewModelScope.launch {
        isAppThemeDarkModeUseCase().collectLatest { isDark ->
            state = state.copy(isAppThemeDarkMode = isDark)
        }
    }

    private fun updateAppTheme(darkMode: Boolean) = viewModelScope.launch {
        updateAppThemeUseCase(darkMode = darkMode)
    }
}