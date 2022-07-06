package example.singletontest.data

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val isAppThemeDarkMode: Flow<Boolean?>
    suspend fun updateAppTheme(darkMode: Boolean)
}