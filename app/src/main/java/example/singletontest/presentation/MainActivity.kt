package example.singletontest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import example.singletontest.domain.type.ScreenRouteType
import example.singletontest.presentation.common.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state = viewModel.state

            val navController = rememberNavController()
            val startDestination = ScreenRouteType.Home

            val isAppThemeDarkMode = state.isAppThemeDarkMode ?: isSystemInDarkTheme()

            if (!state.isLoading) AppTheme(darkTheme = isAppThemeDarkMode) {
                MainNavHost(
                    navController = navController,
                    startDestination = startDestination
                )
            }
        }
    }
}