package example.singletontest.data.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import example.singletontest.data.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesImplDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferences {
    private val tag = this::class.java.simpleName

    override val isAppThemeDarkMode: Flow<Boolean?> = dataStore.data
        .catch { exception ->
            exception.localizedMessage?.let { Log.e(tag, it) }
            emit(emptyPreferences())
        }
        .map { preferences ->
            preferences[PreferencesKeys.IS_APP_THEME_DARK_MODE]
        }

    override suspend fun updateAppTheme(darkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_APP_THEME_DARK_MODE] = darkMode
        }
    }

    private object PreferencesKeys {
        val IS_APP_THEME_DARK_MODE = booleanPreferencesKey(name = "is_app_theme_dark_mode")
    }
}