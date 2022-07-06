package example.singletontest.presentation.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
    primary = PrimaryLightColor,
    onPrimary = OnPrimaryLightColor,
    primaryVariant = PrimaryVariantLightColor,
    secondary = SecondaryLightColor,
    onSecondary = OnSecondaryLightColor,
    secondaryVariant = SecondaryVariantLightColor,
    background = BackgroundLightColor,
    onBackground = OnBackgroundLightColor,
    surface = SurfaceLightColor,
    onSurface = OnSurfaceLightColor,
    error = ErrorLightColor,
    onError = OnErrorLightColor
)

private val DarkColorPalette = darkColors(
    primary = PrimaryDarkColor,
    onPrimary = OnPrimaryDarkColor,
    primaryVariant = PrimaryVariantDarkColor,
    secondary = SecondaryDarkColor,
    onSecondary = OnSecondaryDarkColor,
    secondaryVariant = SecondaryVariantDarkColor,
    background = BackgroundDarkColor,
    onBackground = OnBackgroundDarkColor,
    surface = SurfaceDarkColor,
    onSurface = OnSurfaceDarkColor,
    error = ErrorDarkColor,
    onError = OnErrorDarkColor
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(color = colors.primary)
        systemUiController.systemBarsDarkContentEnabled = false
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalElevating provides Elevating()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                    content = content
                )
            }
        )
    }
}