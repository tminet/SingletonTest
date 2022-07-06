package example.singletontest.presentation.screen_datastore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import example.singletontest.R
import example.singletontest.presentation.common.AppTopBarWithBack
import example.singletontest.presentation.common.theme.elevating
import example.singletontest.presentation.common.theme.spacing

@Composable
fun DataStoreScreen(
    viewModel: DataStoreViewModel = hiltViewModel(),
    navBackToHomeScreen: () -> Unit
) {
    val isAppThemeDarkMode = viewModel.state.isAppThemeDarkMode ?: isSystemInDarkTheme()

    val themeIconState: Pair<ImageVector, String> =
        if (isAppThemeDarkMode) Icons.Rounded.DarkMode to stringResource(id = R.string.darkMode)
        else Icons.Rounded.LightMode to stringResource(id = R.string.lightMode)

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBarWithBack(title = R.string.screenTitleDataStore) {
            navBackToHomeScreen()
        }

        Card(
            modifier = Modifier
                .padding(all = MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .clickable {
                    viewModel.onAction(
                        action = DataStoreAction.ChangeAppTheme(darkMode = !isAppThemeDarkMode)
                    )
                },
            shape = MaterialTheme.shapes.large,
            elevation = MaterialTheme.elevating.none,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = MaterialTheme.spacing.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.switchAppTheme),
                    color = MaterialTheme.colors.onSurface
                )

                Icon(
                    imageVector = themeIconState.first,
                    contentDescription = themeIconState.second,
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}